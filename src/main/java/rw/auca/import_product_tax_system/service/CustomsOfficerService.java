package rw.auca.import_product_tax_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.auca.import_product_tax_system.domain.CustomsOfficer;
import rw.auca.import_product_tax_system.repository.CustomsOfficerRepository;

@Service 
public class CustomsOfficerService {
    

    @Autowired 
    private  CustomsOfficerRepository customsOfficerRepository;

    public String save (CustomsOfficer customsOfficer)
    {
        customsOfficerRepository.save(customsOfficer);
        return  "Customs Officer saved successfully.";
    }

    public List<CustomsOfficer> getAll()
    {
        return customsOfficerRepository.findAll();
    }

    public Optional<CustomsOfficer> getBtId(UUID id)
    {
        return customsOfficerRepository.findById(id);
    }

    public String update (UUID id , CustomsOfficer newData)
    {
        Optional <CustomsOfficer> found = customsOfficerRepository.findById(id);

        if(found.isEmpty())
        {
            return "The customs officer with that id does not exist";
     
        }

        CustomsOfficer customsOfficer = found.get();
        customsOfficer.setFirstName(newData.getFirstName());
        customsOfficer.setLastName(newData.getLastName());
        customsOfficer.setEmail(newData.getEmail());
        customsOfficer.setInspectionBay(newData.getInspectionBay());
        customsOfficerRepository.save(customsOfficer);

        return  "Customs Office Updated successfully";

    }

    public String delete (UUID id)
    {
        if(!customsOfficerRepository.existsById(id))
        {
            return  "The customs officer with that id does not exist";
        }
        customsOfficerRepository.deleteById(id);
        return "Customs officer delete successfully";
    }


    public List <CustomsOfficer> getByProductCategory(String name)
    {
        return customsOfficerRepository.findByProductCategoryName(name);
    }
}
