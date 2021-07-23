package com.example.bikerental.Model;

public class BookingsModel {

    int image;
    String name;
    String brand;
    String price;
    String pickupDate;
    String returnDate;

    public BookingsModel(int image, String name, String brand, String price,String pickupDate,String returnDate){
        this.image=image;
        this.name=name;
        this.brand=brand;
        this.price=price;
        this.pickupDate=pickupDate;
        this.returnDate=returnDate;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }
    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
