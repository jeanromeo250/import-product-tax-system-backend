package rw.auca.import_product_tax_system.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

@Entity 
@Table (name = "product_category")
public class ProductCategory {
    

    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private  UUID id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "productCategories")
    private List<CustomsOfficer> customsOfficers = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CustomsOfficer> getCustomsOfficers() {
        return customsOfficers;
    }

    public void setCustomsOfficers(List<CustomsOfficer> customsOfficers) {
        this.customsOfficers = customsOfficers;
    }
    

}
