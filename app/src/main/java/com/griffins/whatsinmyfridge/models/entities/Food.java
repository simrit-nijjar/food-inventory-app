package com.griffins.whatsinmyfridge.models.entities;

import java.io.Serializable;

public class Food implements Serializable {

    // Properties
    String name;        // Begin with capital
    String expiration;  // mm/dd/yyyy
    String location;    // Fridge, Pantry
    String amount;      // Units include -> items, L,
    String category;    // Categories include -> Beverages, Fruit, Vegetables, etc
    String notes;       // Additional notes for each food item
    int image;
    int image2;

    // Constructor
    public Food(){}

    public Food(String name, String expiration, String location, String amount, String category, String notes, int image, int image2) {
        this.name = name;
        this.expiration = expiration;
        this.location = location;
        this.amount = amount;
        this.category = category;
        this.notes = notes;
        this.image = image;
        this.image2 = image2;
    }

    // Methods

    public void description() {
        System.out.println("Name = " + name);
        System.out.println("Expiration = " + expiration);
        System.out.println("Location = " + location);
        System.out.println("Amount = " + amount);
        System.out.println("Category = " + category);
        System.out.println("Notes = " + notes);
        System.out.println(".");;
    }

    // Getter Methods
    public String getName() {
        return name;
    }

    public String getExpiration() {
        return expiration;
    }

    public String getLocation() {
        return location;
    }

    public String getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getNotes() {
        return notes;
    }

    public int getImage() {
        return image;
    }

    public int getImage2() {
        return image2;
    }


    // Setter Methods
    public void setFoodName(String newName) {
        this.name = newName;
    }

    public void setExpiration(String newExpiration) {
        this.expiration = newExpiration;
    }

    public void setLocation(String newLocation) {
        this.location = newLocation;
    }

    public void setAmount(String newAmount) {
        this.amount = newAmount;
    }

    public void setCategory(String newCategory) {
        this.category = newCategory;
    }

    public void setNotes(String newNotes) {
        this.notes = newNotes;
    }

    public void setImage(int newImage) {
        this.image = newImage;
    }

    public void setImage2(int newImage2) {
        this.image2 = newImage2;
    }


}
