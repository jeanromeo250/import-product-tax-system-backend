package rw.auca.import_product_tax_system.domain;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "import_declaration")
public class ImportDeclaration {
    
    @Id 
    @GeneratedValue (strategy =  GenerationType.UUID)
    private UUID id;

    private LocalDate declarationDate;

    @Enumerated (EnumType.STRING)
    private DeclarationStatus status;

    private String productName;

    private double declaredValue;

    @ManyToOne
    @JoinColumn(name = "importer_id")
    private Importer importer;

    @ManyToOne
    @JoinColumn(name = "customs_officer_id")
    private CustomsOfficer customsOfficer;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDeclarationDate() {
        return declarationDate;
    }

    public void setDeclarationDate(LocalDate declarationDate) {
        this.declarationDate = declarationDate;
    }

    public DeclarationStatus getStatus() {
        return status;
    }

    public void setStatus(DeclarationStatus status) {
        this.status = status;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getDeclaredValue() {
        return declaredValue;
    }

    public void setDeclaredValue(double declaredValue) {
        this.declaredValue = declaredValue;
    }

    public Importer getImporter() {
        return importer;
    }

    public void setImporter(Importer importer) {
        this.importer = importer;
    }

    public CustomsOfficer getCustomsOfficer() {
        return customsOfficer;
    }

    public void setCustomsOfficer(CustomsOfficer customsOfficer) {
        this.customsOfficer = customsOfficer;
    }


    

}
