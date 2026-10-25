package rw.auca.import_product_tax_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


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
    public ResponseEntity <String> delete (@PathVariable UUID id)
    {
        String message = importDeclarationService.delete(id);

        if(message.equals("The imports declaration with that id does not exist"))
        {
            return new ResponseEntity<>(message , HttpStatus.NOT_FOUND);
        }

        if(message.equals("That import declaration has tax assessments , delete them first"))
        {
            return  new ResponseEntity<>(message ,HttpStatus.CONFLICT);
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


    @PatchMapping ("/{id}/status")

    public  ResponseEntity<String> updateStatus (@PathVariable  UUID  id, @RequestParam  DeclarationStatus  status )
    {
        String message = importDeclarationService.updateStatus(id, status);

        if(message.equals("The import declaration with that id does not exist"))
        {
            return new ResponseEntity<>(message , HttpStatus.NOT_FOUND);
        }

        if(message.startsWith("Can not change status "))
        {
            return new ResponseEntity<>(message ,HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(message ,HttpStatus.OK);

    }


    @GetMapping ("/status/by-status")
    public ResponseEntity<List<Object[]>> getStatsByStatus()
    {
        return new ResponseEntity<>(importDeclarationService.getStatsByStatus() ,HttpStatus.OK);
    }

    @PatchMapping("/cancel-day")
    public ResponseEntity<String> cancelDay(@RequestParam UUID customsOfficerId, @RequestParam String date)
    {
        LocalDate day = LocalDate.parse(date);
        return new ResponseEntity<>(importDeclarationService.cancelDay(customsOfficerId, day), HttpStatus.OK);
    }
    




    @GetMapping("/page")
    public ResponseEntity<Page<ImportDeclaration>> getPage(@RequestParam int page, @RequestParam int size, @RequestParam String sort)
    {
        String[] parts = sort.split(",");
        Sort.Direction direction = (parts.length > 1 && parts[1].equalsIgnoreCase("desc")) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, parts[0]));

        return new ResponseEntity<>(importDeclarationService.getPage(pageable), HttpStatus.OK);
    }
    


}
