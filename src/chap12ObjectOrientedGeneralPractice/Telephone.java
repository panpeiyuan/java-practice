package chap12ObjectOrientedGeneralPractice;

public class Telephone {
    private String brand;
    private double price;
    private String color;

    public Telephone() {
    }

    public Telephone(String brand, double price, String color) {
        this.brand = brand;
        this.price = price;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public static double avgprice(Telephone t1,Telephone t2,Telephone t3){
        double avgprice=(t1.price+t2.price+t3.price)/3;
        return avgprice;
    }
}
