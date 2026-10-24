package rw.auca.import_product_tax_system.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import  java.util.List;
import java.util.Optional;

import rw.auca.import_product_tax_system.domain.InspectionBay;

public interface InspectionBayRepository extends  JpaRepository <InspectionBay ,UUID>
{
    
    @Query("SELECT b FROM InspectionBay b WHERE NOT EXISTS (SELECT o FROM CustomsOfficer o WHERE o.inspectionBay = b) ORDER BY b.bayNumber")
    List<InspectionBay> findFreeInspectionBays();
    

    Optional <InspectionBay> findByBayNumber(int bayNumber);
    
}
