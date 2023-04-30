package ma.enset.entities;

public class Computer extends Product {
    private float ram;
    private float disk;
    private int processors;

    public Computer() {
    }

    public Computer(String name, String description, float price, float ram, float disk, int processors) {
        super(name,description,price);
        this.ram = ram;
        this.disk = disk;
        this.processors = processors;
    }

    public float getRam() {
        return ram;
    }

    public void setRam(float ram) {
        this.ram = ram;
    }

    public float getDisk() {
        return disk;
    }

    public void setDisk(float disk) {
        this.disk = disk;
    }

    public int getProcessors() {
        return processors;
    }

    public void setProcessors(int processors) {
        this.processors = processors;
    }

    @Override
    public String toString() {
        return "Computer name: "+this.getName()+", Price: "+this.getPrice()+", RAM: "+ram+", DISK: "+disk+", Processors: "+processors+", Description: "+this.getDescription() ;
    }
    
}
