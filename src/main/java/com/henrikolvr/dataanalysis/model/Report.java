package com.henrikolvr.dataanalysis.model;

public class Report {

    private int customersAmount;
    private int salesmanAmount;
    private String mostExpensiveSaleId;
    private String worstSalesman;
    private String fileName;

    public Report(String fileName) {
        this.fileName = fileName;
    }

    public int getCustomersAmount() {
        return this.customersAmount;
    }

    public int getSalesmanAmount() {
        return this.salesmanAmount;
    }

    public String getMostExpensiveSaleId() {
        return mostExpensiveSaleId;
    }

    public String getWorstSalesman() {
        return worstSalesman;
    }

    public String getFileName() {
        return fileName;
    }

    public void setMostExpensiveSaleId(String mostExpensiveSaleId) {
        this.mostExpensiveSaleId = mostExpensiveSaleId;
    }

    public void setWorstSalesman(String worstSalesman) {
        this.worstSalesman = worstSalesman;
    }

    public void setCustomersAmount(int customersAmount) {
        this.customersAmount = customersAmount;
    }

    public void setSalesmanAmount(int salesmanAmount) {
        this.salesmanAmount = salesmanAmount;
    }

    @Override
    public String toString() {
        return "Report [Customers Amount: " + customersAmount + ", Salesman Amount: " + salesmanAmount
                + ", Most Expensive Sale ID: " + mostExpensiveSaleId + ", Worst Salesman: " + worstSalesman + "]";
    }

}
