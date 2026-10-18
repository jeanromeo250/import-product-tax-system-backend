package rw.auca.import_product_tax_system.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rw.auca.import_product_tax_system.domain.ProductCategory;
import rw.auca.import_product_tax_system.service.ProductCategoryService;

@RestController 
@RequestMapping ("/api/product-categories")
public class ProductCategoryController {
    
    @Autowired 
    private ProductCategoryService productCategoryService;

    @PostMapping ("/save")
    public ResponseEntity<String> save(@RequestBody ProductCategory productCategory)
    {
        String message = productCategoryService.save(productCategory);

        return new ResponseEntity<>(message , HttpStatus.CREATED);

    }

    @GetMapping ("/all")
    public ResponseEntity<List<ProductCategory>>getAll()
    {
        return  new ResponseEntity<>(productCategoryService.getAll() ,HttpStatus.OK);
    }


    @GetMapping ("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id)
    {
        Optional <ProductCategory> found = productCategoryService.getById(id);

        if(found.isEmpty()){
            return new ResponseEntity<>("The product category with that id does not exist",HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(found.get() , HttpStatus.OK);

    }

    @PutMapping ("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id , @RequestBody  ProductCategory productCategory)
    {

        String message = productCategoryService.update(id, productCategory);
            if(message.equals("The product category with that id does not exist"))
            {
                return new ResponseEntity<>(message , HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(message , HttpStatus.OK);
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String>  delete (@PathVariable UUID id ){
        String message = productCategoryService.delete(id);

        if(message.equals("The product category with that id does not exist")){
            return new ResponseEntity<>(message , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(message , HttpStatus.OK);
    }



}
