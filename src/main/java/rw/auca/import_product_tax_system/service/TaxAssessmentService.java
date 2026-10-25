package rw.auca.import_product_tax_system.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.auca.import_product_tax_system.domain.DeclarationStatus;
import rw.auca.import_product_tax_system.domain.ImportDeclaration;
import rw.auca.import_product_tax_system.domain.TaxAssessment;
import rw.auca.import_product_tax_system.repository.ImportDeclarationRepository;
import rw.auca.import_product_tax_system.repository.TaxAssessmentRepository;

@Service 
public class TaxAssessmentService {
    
    @Autowired 
    private TaxAssessmentRepository taxAssessmentRepository;

    @Autowired 
    private  ImportDeclarationRepository importDeclarationRepository;

    public String save (UUID importDeclarationId , TaxAssessment taxAssessment)
    {
        Optional <ImportDeclaration> found  = importDeclarationRepository.findById(importDeclarationId);

        if(found.isEmpty())
        {
            return "The import declaration with that id does not exist";
        }

        ImportDeclaration importDeclaration = found.get();

        if(importDeclaration.getStatus() != DeclarationStatus.CLEARED)
        {
            return "Tax assessment are only for CLEARED import declarations";
        }

        taxAssessment.setAssessedDate(LocalDate.now());
        taxAssessment.setImportDeclaration(importDeclaration);
        taxAssessmentRepository.save(taxAssessment);

        return ("Tax assessment saved successfully");
    }

    public List <TaxAssessment> getByImporter(String tinNumber)
    {
        return  taxAssessmentRepository.findAssessmentsByTinNumber(tinNumber);
    }


    public String delete(UUID id)
    {
      if(!taxAssessmentRepository.existsById(id))
      {
       
        return  "The tax assessment with that id does not exist";
      }
      taxAssessmentRepository.deleteById(id);
      return  "Tax assessment deleted successfully";
    }
    

}
