package rw.auca.import_product_tax_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.auca.import_product_tax_system.domain.ProductCategory;
import rw.auca.import_product_tax_system.repository.ProductCategoryRepository;

@Service 
public class ProductCategoryService {
    
    @Autowired 
    private  ProductCategoryRepository productCategoryRepository ;


    public String save(ProductCategory productCategory)
    {
        productCategoryRepository.save(productCategory);
        return "Product category saved Successfully";

    }

    public List<ProductCategory> getAll()
    {
        return productCategoryRepository.findAll();
    }

    public Optional <ProductCategory> getById(UUID id)
    {
        return productCategoryRepository.findById(id);
    }

    public  String update(UUID id ,  ProductCategory newData)
    {
      Optional<ProductCategory> found = productCategoryRepository.findById(id);

      if(found.isEmpty()){
        return  "The product Category with that id dos not exist.";
      }

      ProductCategory productCategory = found.get();
      productCategory.setName(newData.getName());
      productCategoryRepository.save(productCategory);
      return "Product category updated successfully";

    }

    public String delete (UUID  id)
    {
       if(!productCategoryRepository.existsById(id)){
        return "The product category with that id does not exist";
       }

       productCategoryRepository.deleteById(id);
       return  "Product category delete successfully";
    }


    public List <ProductCategory> getUnused()
    {
        return productCategoryRepository.findUnusedProductCategories();
    }

}
