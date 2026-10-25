package rw.auca.import_product_tax_system.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.auca.import_product_tax_system.domain.CustomsOfficer;
import rw.auca.import_product_tax_system.domain.DeclarationStatus;
import rw.auca.import_product_tax_system.domain.ImportDeclaration;

public interface ImportDeclarationRepository extends JpaRepository <ImportDeclaration, UUID>{
    
    List <ImportDeclaration> findByStatusOrderByDeclarationDateAsc(DeclarationStatus status);
    List <ImportDeclaration> findByDeclarationDateBetweenOrderByDeclarationDateAsc(LocalDate start , LocalDate end);


    boolean existsByCustomsOfficerAndDeclarationDateAndStatusNot(CustomsOfficer customsOfficer , LocalDate declarationDate , DeclarationStatus status);


    @Query("SELECT d.status, COUNT(d) FROM ImportDeclaration d GROUP BY d.status")
    List<Object[]> countDeclarationsPerStatus();


    @Modifying
    @Query("UPDATE ImportDeclaration d SET d.status = :newStatus WHERE d.customsOfficer.id = :customsOfficerId AND d.declarationDate = :date AND d.status <> :clearedStatus")
    int cancelDay(@Param("newStatus") DeclarationStatus newStatus,
              @Param("customsOfficerId") UUID customsOfficerId,
              @Param("date") LocalDate date,
              @Param("clearedStatus") DeclarationStatus clearedStatus);
}
