package com.henrikolvr.dataanalysis.service;

import com.henrikolvr.dataanalysis.dao.FileDAO;
import com.henrikolvr.dataanalysis.exception.InvalidIdException;
import com.henrikolvr.dataanalysis.model.*;

import java.util.List;

public class FileAnalysisService {

    private final String SALESMAN_ID = "001";
    private final String CUSTOMER_ID = "002";
    private final String SALE_ID = "003";
    private FileDAO fileDAO;
    private Report report;
    private ListService listService;

    public FileAnalysisService() {
        this.fileDAO = new FileDAO();
    }

    public void generateReport() {
        for(String files : fileDAO.getAllFilesFromFolder()) {

            String[] pathDataIn = files.split("/");
            String[] fileName = pathDataIn[pathDataIn.length-1].split("//.");

            List<String> file = fileDAO.readFile(files);
            report = new Report(fileName[0]);
            listService = new ListService();

            for(String selectedFile: file) {
                String[] split = selectedFile.split("ç");
                if(split.length == 4) {
                    selectId(split);
                }
            }

            report.setMostExpensiveSaleId(getMostExpensiveSale());
            report.setWorstSalesman(getWorstSalesman());
            fileDAO.writeFile(report);
        }
    }

    public void selectId(String[] line) {
        switch(line[0]) {
            case SALESMAN_ID:
                listService.addSalesman(new Salesman(line[1], line[2], line[3]));
                report.setSalesmanAmount(listService.getSalesman().size());
                break;
            case CUSTOMER_ID:
                listService.addCustomer(new Customer(line[1], line[2], line[3]));
                report.setCustomersAmount(listService.getCustomers().size());
                break;
            case SALE_ID:
                Salesman salesman = listService.getSalesman().stream().filter(s -> s.getName().equals(line[3])).findFirst().orElse(null);

                Sale saleInfo = new Sale(line[1], salesman);
                String[] items = line[2].replaceAll("\\[|\\]", "").split(",");

                for(int i = 0; i < items.length ; i++) {
                    String[] item = items[i].split("-");

                    saleInfo.addItem(new Item(item[0], item[1], item[2]));
                }
                listService.addSales(saleInfo);
                break;
            default:
                throw new InvalidIdException("Id not found!");
        }
    }

    public String getMostExpensiveSale() {
        return listService.getSales().stream().reduce((actual, next) -> actual.getTotalSales() > next.getTotalSales() ? actual : next).get().getSalesId();
    }

    public String getWorstSalesman() {
        return listService.getSalesman().stream().reduce((actual, next) -> sumSalesBySalesman(actual.getName()) < sumSalesBySalesman(next.getName()) ? actual: next).get().getName();
    }

    public double sumSalesBySalesman(String salesman){
        double sum = 0;

        for(Sale sale : listService.getSales()){
            if(sale.getSalesmanName().equals(salesman)){
                sum += sale.getTotalSales();
            }
        }
        return sum;
    }
}
