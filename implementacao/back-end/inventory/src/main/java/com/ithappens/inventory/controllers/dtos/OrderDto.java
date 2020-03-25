package com.ithappens.inventory.controllers.dtos;

import com.ithappens.inventory.controllers.forms.OrderForm;
import com.ithappens.inventory.domains.Branch;
import com.ithappens.inventory.domains.Client;
import com.ithappens.inventory.domains.Employee;
import com.ithappens.inventory.miscs.enums.OrderTypeEnum;

import java.util.Arrays;
import java.util.List;

public class OrderDto {
    private List<String> clientIdentification;
    private List<String> employeeIdentification;
    private List<String> branchLocalization;
    private List<OrderTypeEnum> type;


    public OrderDto(List<Client> clientIdentification, List<Employee> employeeIdentification, List<Branch> branchLocalization) {
        this.clientIdentification = Arrays.asList(clientIdentification.stream().filter(c->c.equals(c)).map(c->c.getIdentification()).toString());
        /*this.employeeIdentification = employeeIdentification;
        this.branchLocalization = branchLocalization;*/
        this.type = Arrays.asList(OrderTypeEnum.values());
    }

    public List<String> getClientIdentification() {
        return clientIdentification;
    }

    public List<String> getEmployeeIdentification() {
        return employeeIdentification;
    }

    public List<String> getBranchLocalization() {
        return branchLocalization;
    }

    public List<OrderTypeEnum> getType() {
        return type;
    }
}
