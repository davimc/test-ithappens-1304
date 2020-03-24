package com.ithappens.inventory.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ithappens.inventory.miscs.enums.PersonTypeEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String identification;
    @Enumerated(EnumType.STRING)
    private PersonTypeEnum personType;
    @OneToMany(mappedBy = "client", targetEntity = Order.class,fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Order> orders;


    public Client() {
    }

    public Client(String name, String identification, PersonTypeEnum type) {
        this.name = name;

        this.identification = identification;
        this.personType = type;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }



    public PersonTypeEnum getPersonType() {
        return personType;
    }

    public void setPersonType(PersonTypeEnum personType) {
        this.personType = personType;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
