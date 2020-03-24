package com.ithappens.inventory.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ithappens.inventory.miscs.enums.OccupationTypeEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Employee  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String identification;
    @OneToMany(mappedBy = "employee", targetEntity = Order.class,cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Order> orders;
    @Enumerated(EnumType.STRING)
    private OccupationTypeEnum occupationType;
    public Employee() {
    }

    public Employee(String name, String identification, OccupationTypeEnum occupation) {
        this.name = name;

        this.identification = identification;
        this.occupationType = occupation;

    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
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

    public Long getId() {
        return id;
    }
    public OccupationTypeEnum getOccupationType() {
        return occupationType;
    }

    public void setOccupationType(OccupationTypeEnum occupationType) {
        this.occupationType = occupationType;
    }
}
