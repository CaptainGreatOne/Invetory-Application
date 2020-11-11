package com.example.menutest;

public class ItemModel {

    private int id;
    private String name;
    private String department;
    private double price;
    private int quantity;
    private String barcode;

    //constructors
    //full
    public ItemModel(int id, String name, String department, double price, int quantity, String barcode) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.price = price;
        this.quantity = quantity;
        this.barcode = barcode;
    }
    //empty
    public ItemModel() {

    }

    //ToString
    @Override
    public String toString() {
        return
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", price=" + price +
                ", quantity=" + quantity;
    }

    //getters n setters n shit
    //id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    //name
    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }

    //dept
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    //price
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    //quantity
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //barcode
    public String getBarcode() {
        return barcode;
    }
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }


}
