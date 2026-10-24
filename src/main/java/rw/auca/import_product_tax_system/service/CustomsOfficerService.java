package rw.auca.import_product_tax_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.auca.import_product_tax_system.domain.CustomsOfficer;
import rw.auca.import_product_tax_system.domain.ProductCategory;
import rw.auca.import_product_tax_system.repository.CustomsOfficerRepository;
import rw.auca.import_product_tax_system.repository.ProductCategoryRepository;

@Service 
public class CustomsOfficerService {
    

    @Autowired 
    private  CustomsOfficerRepository customsOfficerRepository;

    @Autowired 
    private ProductCategoryRepository productCategoryRepository;

    public String save (CustomsOfficer customsOfficer)
    {
        customsOfficerRepository.save(customsOfficer);
        return  "Customs Officer saved successfully.";
    }

    public List<CustomsOfficer> getAll()
    {
        return customsOfficerRepository.findAll();
    }

    public Optional<CustomsOfficer> getById(UUID id)
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


public String addProductCategory(UUID customsOfficerId, UUID productCategoryId)
{
    Optional<CustomsOfficer> officerFound = customsOfficerRepository.findById(customsOfficerId);

    if (officerFound.isEmpty()) {
        return "The customs officer with that id does not exist";
    }

    Optional<ProductCategory> categoryFound = productCategoryRepository.findById(productCategoryId);

    if (categoryFound.isEmpty()) {
        return "That product category does not exist";
    }

    CustomsOfficer customsOfficer = officerFound.get();
    ProductCategory productCategory = categoryFound.get();

    if (customsOfficer.getProductCategories().contains(productCategory)) {
        return "The customs officer already has that product category";
    }

    customsOfficer.getProductCategories().add(productCategory);
    customsOfficerRepository.save(customsOfficer);
    return "Product category added to the customs officer";
}


}
