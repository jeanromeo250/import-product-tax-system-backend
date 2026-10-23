package rw.auca.import_product_tax_system.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import  java.util.List;

import rw.auca.import_product_tax_system.domain.CustomsOfficer;

public interface CustomsOfficerRepository  extends  JpaRepository <CustomsOfficer , UUID>{

    @Query("SELECT o FROM CustomsOfficer o JOIN o.productCategories c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<CustomsOfficer> findByProductCategoryName(@Param("name") String name);

}

