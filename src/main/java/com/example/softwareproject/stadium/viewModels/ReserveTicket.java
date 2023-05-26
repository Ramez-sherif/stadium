package com.example.softwareproject.stadium.viewModels;

import com.example.softwareproject.stadium.models.Category;

public class ReserveTicket {
    Category category;
    double price;
    int quantity;
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
