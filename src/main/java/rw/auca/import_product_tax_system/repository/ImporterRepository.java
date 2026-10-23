package rw.auca.import_product_tax_system.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import rw.auca.import_product_tax_system.domain.Importer;

public interface ImporterRepository extends JpaRepository <Importer , UUID> 
{

    List <Importer> findByCompanyNameIgnoreCaseOrderByContactPersonAsc(String companyName);

}
