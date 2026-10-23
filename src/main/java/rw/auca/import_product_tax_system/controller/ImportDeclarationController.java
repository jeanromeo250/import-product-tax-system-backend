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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import rw.auca.import_product_tax_system.domain.DeclarationStatus;
import rw.auca.import_product_tax_system.domain.ImportDeclaration;
import rw.auca.import_product_tax_system.service.ImportDeclarationService;

@RestController 
@RequestMapping ("/api/import-declarations")
public class ImportDeclarationController {
    
    @Autowired 
    private ImportDeclarationService importDeclarationService;

    @PostMapping ("/save")
    public ResponseEntity<String> save (@RequestBody ImportDeclaration importDeclaration)
    {

    
        String message = importDeclarationService.save(importDeclaration);

        if(message.equals("Import declaratiom date can not be in past"))
        {
            return new ResponseEntity<>(message , HttpStatus.BAD_REQUEST);
        }

        if(message.equals("Customs officer already has a declaration on that date "))
        {
            return new ResponseEntity<>(message , HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping ("/all")
    public ResponseEntity<List<ImportDeclaration>> getAll()
    {
        return new ResponseEntity<>(importDeclarationService.getAll() , HttpStatus.OK);
    }

    @GetMapping ("{id}")
    public ResponseEntity <Object> getById (@PathVariable  UUID id)
    {

        Optional <ImportDeclaration> found = importDeclarationService.getById(id);

        if(found.isEmpty())
        {
            return new ResponseEntity<>("The import declaration with that id does not exist",HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(found.get(),HttpStatus.OK);
    }

    @PutMapping ("/update/{id}")
    public ResponseEntity<String> update (@PathVariable  UUID id , @RequestBody ImportDeclaration importDeclaration)
    {
        String message  = importDeclarationService.update(id, importDeclaration);

        if(message.equals("The import declaration with that id does not exist"))
        {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        return  new ResponseEntity<>(message , HttpStatus.OK);
    }


    @DeleteMapping ("/delete/{id}")
    public  ResponseEntity <String> delete (@PathVariable  UUID id)
    {
        String message  = importDeclarationService.delete(id);

        if(message.equals("The import declaration with that id does not exist"))
        {
            return  new ResponseEntity<> (message , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(message , HttpStatus.OK);


    }


    @GetMapping ("/by-status")
    public ResponseEntity<List<ImportDeclaration>> getByStatus(@RequestParam DeclarationStatus status)
    {
        return new ResponseEntity<>(importDeclarationService.getByStatus(status) , HttpStatus.OK);
    }

    @GetMapping ("/between")

    public  ResponseEntity<List<ImportDeclaration>> getBetween(@RequestParam String start , @RequestParam String end)
    {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        return new ResponseEntity<>(importDeclarationService.getBetween(startDate , endDate) , HttpStatus.OK);
    }



}
