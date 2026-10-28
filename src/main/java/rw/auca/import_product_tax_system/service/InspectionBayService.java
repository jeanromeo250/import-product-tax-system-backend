package rw.auca.import_product_tax_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.auca.import_product_tax_system.domain.InspectionBay;
import rw.auca.import_product_tax_system.repository.InspectionBayRepository;

@Service 
public class InspectionBayService {
    @Autowired 
    private InspectionBayRepository inspectionBayRepository;

    public String save(InspectionBay inspectionBay )
    {
        inspectionBayRepository.save(inspectionBay);
        return "Inspection bay saved successfully.";
    }


    public List<InspectionBay> getAll()
    {
        return  inspectionBayRepository.findAll();
    }


    public Optional<InspectionBay> getById(UUID id)
    {
        return inspectionBayRepository.findById(id);
    }
    public String update(UUID id , InspectionBay newData)
    {
        Optional <InspectionBay> found = inspectionBayRepository.findById(id);

        if(found.isEmpty())
        {
        }

        InspectionBay inspectionBay = found.get();
        inspectionBay.setBayNumber(newData.getBayNumber());
        inspectionBay.setName(newData.getName());
        inspectionBayRepository.save(inspectionBay);

        return "Inspection bay  updated successfully.";
    }

    public String delete(UUID id)
    {
        if(!inspectionBayRepository.existsById(id))
        {
        }

        inspectionBayRepository.deleteById(id);
        return  "Inspection  bay deleted successfully.";
    }

    public List <InspectionBay> getFree()
    {
        return inspectionBayRepository.findFreeInspectionBays();
    }


}
