package com.henrikolvr.dataanalysis.service;

import com.henrikolvr.dataanalysis.model.Customer;
import com.henrikolvr.dataanalysis.model.Sale;
import com.henrikolvr.dataanalysis.model.Salesman;

import java.util.ArrayList;
import java.util.List;

public class ListService {

    private List<Salesman> listSalesman;
    private List<Customer> listCustomer;
    private List<Sale> listSales;

    public ListService() {
        this.listSalesman = new ArrayList<>();
        this.listCustomer = new ArrayList<>();
        this.listSales = new ArrayList<>();
    }
    public void addSalesman(Salesman salesman) {
        listSalesman.add(salesman);
    }

    public void addCustomer(Customer customer) {
        listCustomer.add(customer);

    }

    public void addSales(Sale sale) {
        listSales.add(sale);
    }

    public List<Salesman> getSalesman(){
        return listSalesman;
    }

    public List<Customer> getCustomers(){
        return listCustomer;
    }

    public List<Sale> getSales(){
        return listSales;
    }
}
