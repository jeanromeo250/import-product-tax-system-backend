package rw.auca.import_product_tax_system.repository;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.auca.import_product_tax_system.domain.TaxAssessment;

public interface TaxAssessmentRepository extends JpaRepository  <TaxAssessment , UUID > 
{

@Query("SELECT t FROM TaxAssessment t WHERE t.importDeclaration.importer.tinNumber = :tinNumber ORDER BY t.assessedDate DESC")
List<TaxAssessment> findAssessmentsByTinNumber(@Param("tinNumber") String tinNumber);


}
