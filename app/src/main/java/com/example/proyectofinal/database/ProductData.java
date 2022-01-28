package com.example.proyectofinal.database;

public class ProductData {

    private int id;
    private String name;
    private String brand;
    private String category;
    private int stock;
    private double price;

    public ProductData(String name, String brand, String category, int stock, double price) {
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.stock = stock;
        this.price = price;
    }

    public ProductData(int id, String name, String brand, String category, int stock, double price) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.stock = stock;
        this.price = price;
    }

    public ProductData (){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
