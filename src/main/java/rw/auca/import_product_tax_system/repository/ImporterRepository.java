package rw.auca.import_product_tax_system.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.auca.import_product_tax_system.domain.Importer;

public interface ImporterRepository extends JpaRepository <Importer , UUID> 
{

    List <Importer> findByCompanyNameIgnoreCaseOrderByContactPersonAsc(String companyName);

    @Query("SELECT DISTINCT d.importer FROM ImportDeclaration d WHERE d.customsOfficer.id = :customsOfficerId")
    List<Importer> findImportersByCustomsOfficer(@Param("customsOfficerId") UUID customsOfficerId);

}
