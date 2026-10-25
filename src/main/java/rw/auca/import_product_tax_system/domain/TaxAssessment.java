package rw.auca.import_product_tax_system.domain;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "tax_assessment")
public class TaxAssessment {
    @Id 
    @GeneratedValue (strategy =  GenerationType.UUID)
    private  UUID id;

    private String taxName;
    private String remarks;

    private int amount ;
    private LocalDate assessedDate;

    @ManyToOne
    @JoinColumn(name = "import_declaration_id")
    private ImportDeclaration importDeclaration;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    

    public ImportDeclaration getImportDeclaration() {
        return importDeclaration;
    }

    public void setImportDeclaration(ImportDeclaration importDeclaration) {
        this.importDeclaration = importDeclaration;
    }

    public LocalDate getAssessedDate() {
        return assessedDate;
    }

    public void setAssessedDate(LocalDate assessedDate) {
        this.assessedDate = assessedDate;
    }

}
