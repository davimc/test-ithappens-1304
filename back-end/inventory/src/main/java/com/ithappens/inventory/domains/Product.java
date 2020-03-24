package com.ithappens.inventory.domains;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
public class Product  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;
    @NotEmpty(message="barcode")
    private String barcode;
    @NotEmpty(message = "sequential")
    private String sequential;

    public Product() {
    }

    public Product(String description, String barcode, String sequential) {

        this.description = description;
        this.barcode = barcode;
        this.sequential = sequential;

    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }



    public void setDescription(String description) {
        this.description = description;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getSequential() {
        return sequential;
    }

    public void setSequential(String sequential) {
        this.sequential = sequential;
    }


}
