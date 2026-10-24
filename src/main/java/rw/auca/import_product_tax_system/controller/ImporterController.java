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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rw.auca.import_product_tax_system.domain.Importer;
import rw.auca.import_product_tax_system.service.CustomsOfficerService;
import rw.auca.import_product_tax_system.service.ImporterService;

@RestController 
@RequestMapping  ("/api/importers")
public class ImporterController {
    
    @Autowired 
    private ImporterService importerService;

    @Autowired 
    private CustomsOfficerService customsOfficerService;


    @PostMapping ("/save")
    public ResponseEntity<String> save(@RequestBody Importer importer)
    {
        String message = importerService.save(importer);
        return new ResponseEntity<>(message , HttpStatus.CREATED);

    }


    @GetMapping ("all")
    public ResponseEntity <List<Importer>> getAll()
    {
        return new ResponseEntity<>(importerService.getAll(), HttpStatus.OK);

    }

    @GetMapping ("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id)
    {
        Optional <Importer> found = importerService.getById(id);
        
        if(found.isEmpty())
        {
            return new ResponseEntity<>("The importer with that id does not exist",HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(found.get() , HttpStatus.OK);
    }

    @PutMapping ("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id , @RequestBody Importer importer)
    {
        String  message = importerService.update(id, importer);

       if(message.equals("The importer with that id does not exis"))
       {
        return new ResponseEntity<>(message ,HttpStatus.NOT_FOUND);
       }

       return  new ResponseEntity<>(message , HttpStatus.OK);

    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity <String> delete(@PathVariable UUID id)
    {
        String message = importerService.delete(id);

        if(message.equals("The importer with that id does not exist"))
        {
            return new ResponseEntity<>(message , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(message , HttpStatus.OK);
    }


    @GetMapping ("/by-company-name")
    public  ResponseEntity<List<Importer>> getByCompanyName(@RequestParam String companyName)
    {
        return  new ResponseEntity<>(importerService.getByCompanyName(companyName),HttpStatus.OK);
    }


    @GetMapping ("/of-customs-officer/{customsOfficerId}")
    public ResponseEntity <Object> getByCustomsOfficer(@PathVariable UUID customsOfficerId)
    {
        if(customsOfficerService.getById(customsOfficerId).isEmpty())
        {
            return  new ResponseEntity<>("The customs officer with that id does not exist" , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(importerService.getByCustomsOfficer(customsOfficerId) , HttpStatus.OK);
    }

}
