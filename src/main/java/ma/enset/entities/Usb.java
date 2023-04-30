package ma.enset.entities;

public class Usb extends Product {
    private float capacity;

    public Usb() {
    }

    public Usb(String name, String Description, float price, float capacity) {
        super(name, Description, price);
        this.capacity = capacity;
    }

    public float getCapacity() {
        return capacity;
    }

    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "USB name: "+this.getName()+", Price: "+this.getPrice()+", Capacity: "+capacity+", Description: "+this.getDescription() ;
    }
}
