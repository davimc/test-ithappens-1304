package com.ithappens.inventory.domains;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ithappens.inventory.miscs.enums.ItemStatusEnum;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Entity
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;
    @OneToOne
    @JoinColumn(name="stock_id")

    private Stock stock;
    @Enumerated(EnumType.STRING)
    private ItemStatusEnum itemStatus;
    @PositiveOrZero
    private int quantity;

    public Item() {
    }

    public Item(Order order, Stock stock) {
        this.order = order;
        this.stock = stock;
        this.itemStatus = ItemStatusEnum.ACTIVE;
        quantity = 1;
    }

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public ItemStatusEnum getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(ItemStatusEnum itemStatus) {
        this.itemStatus = itemStatus;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void addQuantity(int quantity) {this.quantity+=quantity;}
    public void removeQuantity(int quantity) {this.quantity-=quantity;}
    public void cancelStatus(){
        setItemStatus(ItemStatusEnum.CANCELED);
    }
    public boolean processStatus(){
        if(getItemStatus()==ItemStatusEnum.ACTIVE) {
            setItemStatus(ItemStatusEnum.PROCESSED);
            return true;
        }
        return false;
    }
}
