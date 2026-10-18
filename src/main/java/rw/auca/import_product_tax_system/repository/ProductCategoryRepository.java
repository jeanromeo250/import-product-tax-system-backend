package rw.auca.import_product_tax_system.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import rw.auca.import_product_tax_system.domain.ProductCategory;

public interface ProductCategoryRepository  extends  JpaRepository<ProductCategory , UUID>{

    
    
}
