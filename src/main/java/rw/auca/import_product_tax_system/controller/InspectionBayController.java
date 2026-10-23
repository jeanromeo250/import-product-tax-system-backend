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

import rw.auca.import_product_tax_system.domain.InspectionBay;
import rw.auca.import_product_tax_system.service.InspectionBayService;

@RestController 
@RequestMapping ("/api/inspectionBays")

public class InspectionBayController {
    @Autowired 
    private InspectionBayService inspectionBayService;



    @PostMapping ("/save")
    public ResponseEntity<String> save(@RequestBody InspectionBay inspectionBay)
    {
        String message = inspectionBayService.save(inspectionBay);
        return  new ResponseEntity<>(message , HttpStatus.CREATED);
    }


    @GetMapping ("/all")
    public ResponseEntity<List<InspectionBay>> getAll()
    {
        return  new ResponseEntity<>(inspectionBayService.getAll() , HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id)
    {

        Optional <InspectionBay> found = inspectionBayService.getById(id);
        if(found.isEmpty())
        {
            return new ResponseEntity<>("The inspection bay with that id does not exist",HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(found.get() , HttpStatus.OK);
    }


    @PutMapping ("/update/{id}")
    public  ResponseEntity <String> update (@PathVariable UUID id , @RequestBody InspectionBay inspectionBay)
    {
        String message = inspectionBayService.update(id, inspectionBay);

        if(message.equals("The inspection bay  with that id does not exist"))
        {
            return  new ResponseEntity<>(message , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(message , HttpStatus.OK);
    }


    @DeleteMapping ("/delete/{id}")
    public  ResponseEntity<String> delete(@PathVariable  UUID id )
    {
        String message = inspectionBayService.delete(id);

        if(message.equals("The inspection bay  with that id does not exist"))
        {
            return  new ResponseEntity<>(message , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(message , HttpStatus.OK);
    }



    @GetMapping ("/free")
    public ResponseEntity <List<InspectionBay>> getFree()
    {
        
        return  new ResponseEntity<>(inspectionBayService.getFree(),HttpStatus.OK);
    }





}
