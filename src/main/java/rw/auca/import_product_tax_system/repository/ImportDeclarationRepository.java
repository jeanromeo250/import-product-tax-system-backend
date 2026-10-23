package rw.auca.import_product_tax_system.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import rw.auca.import_product_tax_system.domain.CustomsOfficer;
import rw.auca.import_product_tax_system.domain.DeclarationStatus;
import rw.auca.import_product_tax_system.domain.ImportDeclaration;

public interface ImportDeclarationRepository extends JpaRepository <ImportDeclaration, UUID>{
    
    List <ImportDeclaration> findByStatusOrderByDeclarationDateAsc(DeclarationStatus status);
    List <ImportDeclaration> findByDeclarationDateBetweenOrderByDeclarationDateAsc(LocalDate start , LocalDate end);


    boolean existsByCustomsOfficerAndDeclarationDateAndStatusNot(CustomsOfficer customsOfficer , LocalDate declarationDate , DeclarationStatus status);

}
