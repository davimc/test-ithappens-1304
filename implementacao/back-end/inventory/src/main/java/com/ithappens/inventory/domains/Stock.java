package com.ithappens.inventory.domains;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Entity
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="product_id")
    @JsonManagedReference
    private Product product;
    @ManyToOne
    @JoinColumn(name="branch_id")
    @JsonManagedReference
    private Branch branch;
    @PositiveOrZero(message = "quantity")
    private int quantity;
    @PositiveOrZero(message = "price")
    private double price;

    public Stock() {
    }

    public Stock(Branch branch, Product product,int quantity, double price) {
        this.branch = branch;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Branch getBranch() {
        return branch;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(@PositiveOrZero(message = "Quantidade precisa ser maior que zero") double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void modifyQuantity(int quantity) {this.quantity +=quantity;}
}
