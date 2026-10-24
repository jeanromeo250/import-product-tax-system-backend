package rw.auca.import_product_tax_system.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import rw.auca.import_product_tax_system.domain.TaxAssessment;

public interface TaxAssessmentRepository extends JpaRepository  <TaxAssessment , UUID > 
{

    
    
}
