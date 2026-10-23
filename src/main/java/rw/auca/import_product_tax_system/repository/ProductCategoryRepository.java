package rw.auca.import_product_tax_system.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import rw.auca.import_product_tax_system.domain.ProductCategory;

public interface ProductCategoryRepository  extends  JpaRepository<ProductCategory , UUID>{

    
    @Query("SELECT c FROM ProductCategory c WHERE c.customsOfficers IS EMPTY")
    List<ProductCategory> findUnusedProductCategories();
}
