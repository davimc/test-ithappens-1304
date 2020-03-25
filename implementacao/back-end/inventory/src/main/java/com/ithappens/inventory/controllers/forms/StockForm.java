package com.ithappens.inventory.controllers.forms;

import com.ithappens.inventory.domains.Item;
import com.ithappens.inventory.domains.Stock;
import org.springframework.beans.factory.annotation.Autowired;

public class StockForm {
    private String productDescrition;
    private String productBarcode;
    private String productSequential;
    private String obs = "";

    public StockForm() {
    }

    public StockForm(String obs) {
        this.obs = obs;
    }

    public StockForm(Item item) {
        this.productDescrition = item.getStock().getProduct().getDescription();
        this.productBarcode = item.getStock().getProduct().getBarcode();
        this.productSequential = item.getStock().getProduct().getSequential();
    }

    public StockForm(String productDescrition, String productBarcode, String productSequential) {

        this.productDescrition = productDescrition;
        this.productBarcode = productBarcode;
        this.productSequential = productSequential;
    }

    public String getProductDescrition() {
        return productDescrition;
    }

    public String getProductBarcode() {
        return productBarcode;
    }
    
    

    public String getProductSequential() {
        return productSequential;
    }

    public String getObs() {
        return obs;
    }
}
