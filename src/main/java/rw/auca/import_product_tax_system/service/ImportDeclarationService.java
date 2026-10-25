package rw.auca.import_product_tax_system.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rw.auca.import_product_tax_system.domain.DeclarationStatus;
import rw.auca.import_product_tax_system.domain.ImportDeclaration;
import rw.auca.import_product_tax_system.repository.ImportDeclarationRepository;
import rw.auca.import_product_tax_system.repository.TaxAssessmentRepository;

@Service 
public class ImportDeclarationService {
    
    @Autowired 
    private ImportDeclarationRepository importDeclarationRepository;

    @Autowired 
    private TaxAssessmentRepository taxAssessmentRepository;

    public String save(ImportDeclaration importDeclaration)
    {
      if(importDeclaration.getDeclarationDate().isBefore(LocalDate.now()))
      {
        return "Import declaration date can not be in the past";
      }

      if(importDeclarationRepository.existsByCustomsOfficerAndDeclarationDateAndStatusNot(
        importDeclaration.getCustomsOfficer(),
        importDeclaration.getDeclarationDate(),
        DeclarationStatus.REJECTED))
        {
            return  "Customs officer already has a declaration on that date";
        }

        importDeclaration.setStatus(DeclarationStatus.SUBMITTED);
        importDeclarationRepository.save(importDeclaration);
        return "Import declaration saved successfully";

    }


    public List <ImportDeclaration> getAll()
    {
        return  importDeclarationRepository.findAll();
    }

    public Optional<ImportDeclaration> getById(UUID id)
    {
        return importDeclarationRepository.findById(id);
    }

    public String update(UUID id , ImportDeclaration newData)
    {
        Optional <ImportDeclaration> found = importDeclarationRepository.findById(id);

        if(found.isEmpty())
        {
            return "The import declaration with that id does not exist";

        }

        ImportDeclaration importDeclaration = found.get();

        importDeclaration.setDeclarationDate(newData.getDeclarationDate());
        importDeclaration.setProductName(newData.getProductName());
        importDeclaration.setDeclaredValue(newData.getDeclaredValue());
        importDeclaration.setImporter(newData.getImporter());
        importDeclaration.setCustomsOfficer(newData.getCustomsOfficer());


        importDeclarationRepository.save(importDeclaration);

        return "The import declaration updated successfully";
    }

    public  String delete(UUID id){
        Optional <ImportDeclaration>  found = importDeclarationRepository.findById(id);

        if(found.isEmpty())
        {
            return "The import declaration with that id does not exist";
        }

         if (taxAssessmentRepository.existsByImportDeclaration(found.get()))
            
        {
            return "That import declaration has tax assessments, delete them first";
        }

        importDeclarationRepository.deleteById(id);
        return  "Import declaration deleted successfully";
    }

    // FIND THE IMPORTED DECLARATION BY ORDER STATUS (APPROVED ,REJECTED OR ANOTHER )

    public List< ImportDeclaration> getByStatus(DeclarationStatus status)
    {
        return importDeclarationRepository.findByStatusOrderByDeclarationDateAsc(status);
    }

    // FIND DECLARATION BETWEEN TWO DATE 

    public List <ImportDeclaration> getBetween(LocalDate start , LocalDate end)
    {
        return importDeclarationRepository.findByDeclarationDateBetweenOrderByDeclarationDateAsc(start, end);
    }



    public String updateStatus (UUID id , DeclarationStatus newStatus)
    {
        Optional <ImportDeclaration> found = importDeclarationRepository.findById(id);

        if(found.isEmpty())
        {
            return "The import declaration with that id does not exist";
        }

        ImportDeclaration importDeclaration = found.get();
        DeclarationStatus current = importDeclaration.getStatus();

        boolean allowed = (current == DeclarationStatus.SUBMITTED && (newStatus == DeclarationStatus.UNDER_ASSESSMENT || newStatus == DeclarationStatus.REJECTED))

                         || (current == DeclarationStatus.UNDER_ASSESSMENT && (newStatus == DeclarationStatus.CLEARED || newStatus == DeclarationStatus.REJECTED));

       
        if (!allowed) {
            return  "Con not change status from " + current + "to " +newStatus;
            
        }

        importDeclaration.setStatus(newStatus);
        importDeclarationRepository.save(importDeclaration);
        return "Import declaration status updated successfully";
                        
    }


    public List<Object[]> getStatsByStatus()
    {
        return  importDeclarationRepository.countDeclarationsPerStatus();
    }


    @Transactional 
    public  String cancelDay(UUID customsOfficerId, LocalDate date)
    {
        int count = importDeclarationRepository.cancelDay(DeclarationStatus.REJECTED, customsOfficerId, date, DeclarationStatus.CLEARED);
        return  count + "import declaration cancelled";
    }




}
