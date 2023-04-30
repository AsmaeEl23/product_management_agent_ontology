package ma.enset.entities;

import jade.content.Concept;

public class Product implements Concept {
    private String name;
    private String Description;
    private float price;

    public Product() {
    }

    public Product(String name, String Description, float price) {
        this.name = name;
        this.Description = Description;
        this.price = price;
    }
    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
