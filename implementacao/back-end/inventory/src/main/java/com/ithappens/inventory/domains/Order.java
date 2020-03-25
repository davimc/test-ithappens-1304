package com.ithappens.inventory.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ithappens.inventory.miscs.enums.OrderTypeEnum;
import com.ithappens.inventory.miscs.enums.PaymentFormatEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="order_table")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="branch_id")
    @JsonManagedReference
    private Branch branch;
    @OneToMany(mappedBy = "order", cascade=CascadeType.ALL)
    @JsonBackReference
    private List<Item> items;
    @ManyToOne
    @JoinColumn(name="client_id")
    @JsonManagedReference
    private Client client;
    @ManyToOne
    @JoinColumn(name="employee_id")
    @JsonManagedReference
    private Employee employee;

    @Enumerated(EnumType.STRING)
    private OrderTypeEnum orderType;

    private String obsDelivery;
    @Enumerated(EnumType.STRING)
    private PaymentFormatEnum payment;
    private double total;
    //private Calendar dtPedido;


    public Order() {
    }

    public Order(Branch branch, Client client, Employee employee, OrderTypeEnum type, String obsDelivery) {
        this.branch = branch;
        this.client = client;
        this.employee = employee;
        this.orderType = type;
        this.obsDelivery = obsDelivery;
        this.items = new ArrayList<>();
        total = 0;
    }

    public Long getId() {
        return id;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public OrderTypeEnum getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderTypeEnum orderType) {
        this.orderType = orderType;
    }

    public String getObsDelivery() {
        return obsDelivery;
    }

    public void setObsDelivery(String obsDelivery) {
        this.obsDelivery = obsDelivery;
    }

    public PaymentFormatEnum getPayment() {
        return payment;
    }

    public void setPayment(PaymentFormatEnum payment) {
        this.payment = payment;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void addItem(Item item){
        items.add(item);
    }
    public void incrimentQuantityItem(Item item, int quantity){items.get(items.indexOf(item)).addQuantity(quantity);}

    public void sumTotal(double price, int quantity){
        total+= price*quantity;
    }
    public void subtractsTotal(double price, int quantity){
        total-= price*quantity;
    }
}
