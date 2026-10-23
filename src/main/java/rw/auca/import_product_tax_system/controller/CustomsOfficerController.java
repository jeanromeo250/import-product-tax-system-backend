package rw.auca.import_product_tax_system.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import  java.util.List;
import java.util.Optional;
import java.util.UUID;

import rw.auca.import_product_tax_system.domain.CustomsOfficer;
import rw.auca.import_product_tax_system.service.CustomsOfficerService;

@RestController 
@RequestMapping  ("/api/customsOfficers")
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
        Optional<CustomsOfficer> found = customsOfficerService.getBtId(id);

        if (found.isEmpty()) {
            return new ResponseEntity<>("The customs officer with that id does not exist", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(found.get(), HttpStatus.OK);
    }

    @PutMapping ("/update/{id}")
    public ResponseEntity<String> update (@PathVariable UUID id ,@RequestBody CustomsOfficer customsOfficer )
    {
        String message = customsOfficerService.update(id, customsOfficer);
        if(message.equals("The customs Officer with that id does not exist"))
        {
            return new ResponseEntity<>(message , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @DeleteMapping ("/delete/{id}")
    public ResponseEntity <String> delete (@PathVariable  UUID id)
    {
        String message = customsOfficerService.delete(id);
        if(message.equals("The customs officer"))
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


}
