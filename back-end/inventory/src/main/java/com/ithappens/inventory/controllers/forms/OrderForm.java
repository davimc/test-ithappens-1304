package com.ithappens.inventory.controllers.forms;

import com.ithappens.inventory.domains.Order;
import com.ithappens.inventory.miscs.enums.OrderTypeEnum;
import com.ithappens.inventory.miscs.enums.PaymentFormatEnum;

public class OrderForm {
    private String branchLocation;
    private String clientIdentification;
    private String employeeIdentification;
    private OrderTypeEnum type;
    private String obs;
    private PaymentFormatEnum payment;
    private double total;

    public OrderForm() {
    }

    public OrderForm(PaymentFormatEnum payment) {
        this.payment = payment;
    }

    public OrderForm(String branchLocation, String clientIdentification, String employeeIdentification, OrderTypeEnum type,String obs) {
        this.branchLocation = branchLocation;
        this.clientIdentification = clientIdentification;
        this.employeeIdentification = employeeIdentification;
        this.type = type;
        this.obs = obs;
    }
    public OrderForm(Order order) {
        clientIdentification = order.getClient().getIdentification();
        branchLocation = order.getBranch().getLocation();
        employeeIdentification = order.getEmployee().getIdentification();
        type = order.getOrderType();
        obs = order.getObsDelivery();
        payment = order.getPayment();
        total = order.getTotal();
    }


    /*GETTERS*/
    public String getBranchLocation() {
        return branchLocation;
    }

    public String getClientIdentification() {
        return clientIdentification;
    }

    public String getEmployeeIdentification() {
        return employeeIdentification;
    }

    public OrderTypeEnum getType() {
        return type;
    }

    public String getObs() {
        return obs;
    }

    public double getTotal() {
        return total;
    }

    public PaymentFormatEnum getPayment() {
        return payment;
    }
    //    public Order converter(){
//        return new Order(get)
//    }
}
