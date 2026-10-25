package rw.auca.import_product_tax_system.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rw.auca.import_product_tax_system.domain.TaxAssessment;
import rw.auca.import_product_tax_system.service.TaxAssessmentService;

@RestController 
@RequestMapping ("/api/tax-assessments")
public class TaxAssessmentController {

    @Autowired 
    private  TaxAssessmentService taxAssessmentService;

    @PostMapping ("/save")
    public ResponseEntity<String> save(@RequestParam UUID importDeclarationId , @RequestBody  TaxAssessment taxAssessment)
    {
        String message =  taxAssessmentService.save(importDeclarationId, taxAssessment);

        if(message.equals("The import declaration with that id does not exist"))
        {
            return new ResponseEntity<>(message , HttpStatus.NOT_FOUND);
        }

        if(message.equals("Tax assessments are only for CLEARED import declaration"))
        {
            return new ResponseEntity<>(message , HttpStatus.BAD_REQUEST);
        }

        return  new ResponseEntity<>(message , HttpStatus.CREATED);
    }

    @GetMapping ("/importer/{tinNumber}")
    public ResponseEntity <List<TaxAssessment>> getByImporter(@PathVariable String tinNumber)
    {
        return new ResponseEntity<>(taxAssessmentService.getByImporter(tinNumber) ,HttpStatus.OK);
    }
    

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity <String> delete (@PathVariable  UUID id)
    {
        String  message =  taxAssessmentService.delete(id);

        if(message.equals("The tax assessment with that id does not exit"))
        {
            return new ResponseEntity<>(message , HttpStatus.NOT_FOUND);
        }

        return  new ResponseEntity<>(message , HttpStatus.OK);
    }

    
}
