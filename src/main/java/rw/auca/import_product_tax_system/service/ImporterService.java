package rw.auca.import_product_tax_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.auca.import_product_tax_system.domain.Importer;
import rw.auca.import_product_tax_system.repository.ImporterRepository;

@Service 
public class ImporterService {
    @Autowired 
    private  ImporterRepository importerRepository;

    public String save (Importer importer)
    {
        importerRepository.save(importer);
        return "Importer saved successfully.";
    }


    public List<Importer> getAll()
    {
        return importerRepository.findAll();
    }

    public Optional<Importer> getById(UUID id)
    {
        return importerRepository.findById(id);
    }

    public String update(UUID id , Importer newData)
    {
        Optional <Importer> found = importerRepository.findById(id);

        if(found.isEmpty())
        {
            return "The importer with that id does not exist";
        }

        Importer importer = found.get();
        importer.setCompanyName(newData.getCompanyName());
        importer.setContactPerson(newData.getContactPerson());
        importer.setTinNumber(newData.getTinNumber());
        importer.setPhone(newData.getPhone());
        importer.setEmail(newData.getEmail());

        importerRepository.save(importer);

        return  "Importer update successfully";
 
    }

    public String delete (UUID id)
    {
        if(!importerRepository.existsById(id))
        {
            return "The importer with that id does not exist";
        }

        importerRepository.deleteById(id);
        return  "Importer deleted successfully.";

    }


    public List<Importer> getByCompanyName (String companyName)
    {
        return importerRepository.findByCompanyNameIgnoreCaseOrderByContactPersonAsc(companyName);
    }

}
