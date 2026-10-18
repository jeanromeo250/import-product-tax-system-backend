package rw.auca.import_product_tax_system.domain;

import java.util.ArrayList;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.List;

@Entity 
@Table (name = "customs_officer")
public class CustomsOfficer {

    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;

    private  String lastName;

    private String email;

    @ManyToOne 
    @JoinColumn (name =  "inspection_bay_id")
    private  InspectionBay inspectionBay;


    @ManyToMany
    @JoinTable(name = "customs_officer_product_category")
    private List<ProductCategory> productCategories = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ProductCategory> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(List<ProductCategory> productCategories) {
        this.productCategories = productCategories;
    }

    public InspectionBay getInspectionBay() {
        return inspectionBay;
    }

    public void setInspectionBay(InspectionBay inspectionBay) {
        this.inspectionBay = inspectionBay;
    }

    
}
