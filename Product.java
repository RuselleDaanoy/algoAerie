package com.example;

public class Product {
    String name;
    double weight;
    int amount;

    public Product(String name, double weight, int amount) {
        this.name = name;
        this.weight = weight;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("%-15s%-10s%-10s", name, weight, amount);
    }
}
