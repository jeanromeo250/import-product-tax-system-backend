package rw.auca.import_product_tax_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.auca.import_product_tax_system.domain.ImportDeclaration;
import rw.auca.import_product_tax_system.repository.ImportDeclarationRepository;

@Service 
public class ImportDeclarationService {
    
    @Autowired 
    private ImportDeclarationRepository importDeclarationRepository;

    public String save(ImportDeclaration importDeclaration)
    {
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
        if(!importDeclarationRepository.existsById(id))
        {
            return "The import declaration with that id does not exist";

        }

        importDeclarationRepository.deleteById(id);
        return  "Import declaration deleted successfully";
    }
}
