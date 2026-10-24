package rw.auca.import_product_tax_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import  java.util.List;
import java.util.Optional;
import java.util.UUID;

import rw.auca.import_product_tax_system.domain.CustomsOfficer;
import rw.auca.import_product_tax_system.service.CustomsOfficerService;

@RestController 
@RequestMapping  ("/api/customs-officers")
public class CustomsOfficerController {

    @Autowired 
    private CustomsOfficerService customsOfficerService;

    @PostMapping ("/save")
    public ResponseEntity<String> save (@RequestBody CustomsOfficer customsOfficer)
    {
        String message = customsOfficerService.save(customsOfficer);
        return new ResponseEntity<>(message , HttpStatus.CREATED);

    }


    @GetMapping ("/all")
    public ResponseEntity <List<CustomsOfficer>> getAll()
    {
        return  new ResponseEntity<>(customsOfficerService.getAll() , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<CustomsOfficer> found = customsOfficerService.getById(id);

        if (found.isEmpty()) {
            return new ResponseEntity<>("The customs officer with that id does not exist", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(found.get(), HttpStatus.OK);
    }

    @PutMapping ("/update/{id}")
    public ResponseEntity<String> update (@PathVariable UUID id ,@RequestBody CustomsOfficer customsOfficer )
    {
        String message = customsOfficerService.update(id, customsOfficer);
        if(message.equals("The customs officer with that id does not exist"))
        {
            return new ResponseEntity<>(message , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @DeleteMapping ("/delete/{id}")
    public ResponseEntity <String> delete (@PathVariable  UUID id)
    {
        String message = customsOfficerService.delete(id);
        if(message.equals("The customs officer with that id does not exist "))
        {
            return new ResponseEntity<>(message , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message , HttpStatus.OK);
    }


    @GetMapping ("/by-product-category")
    public ResponseEntity<List<CustomsOfficer>> getByProductCategory(@RequestParam  String name)
    {
        return new ResponseEntity<>(customsOfficerService.getByProductCategory(name) , HttpStatus.OK);
    }


    @PutMapping ("/{customsOfficerId}/product-categories/{productCategoryId}")
    
    public  ResponseEntity<String> addProductCategory(@PathVariable  UUID customsOfficerId , @PathVariable UUID productCategoryId)
    {
        String message =  customsOfficerService.addProductCategory(customsOfficerId, productCategoryId);

        if(message.equals("The customs officer with that id does not exist") || message.equals("That product category does not exist"))
        {

            return new ResponseEntity<>(message , HttpStatus.NOT_FOUND);
        }

        if(message.equals("The customs officer already has that product category"))
        {
            return new ResponseEntity<>(message , HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(message , HttpStatus.OK);
    }



    @PatchMapping ("/{customsOfficerId}/inspection-bay")
    public ResponseEntity<String> changeInspectionBay(@PathVariable UUID customsOfficerId , @RequestParam  int bayNumber)
    {
        String message = customsOfficerService.changeInspectionBay(customsOfficerId, bayNumber);

        if(message.equals("The customs officer with that id does not exist") || message.equals("The inspection bay with that bayNumber does not exist"))
        {
            return new ResponseEntity<>(message , HttpStatus.NOT_FOUND);
        }

        if(message.equals("That inspection bay is taken by another customs officer"))
        {
            return new ResponseEntity<>(message , HttpStatus.CONFLICT);

        }

        return new ResponseEntity<>(message , HttpStatus.OK);
    }

}
