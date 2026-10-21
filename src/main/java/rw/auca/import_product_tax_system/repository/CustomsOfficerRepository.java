package rw.auca.import_product_tax_system.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import rw.auca.import_product_tax_system.domain.CustomsOfficer;

public interface CustomsOfficerRepository  extends  JpaRepository <CustomsOfficer , UUID>{

    
}

