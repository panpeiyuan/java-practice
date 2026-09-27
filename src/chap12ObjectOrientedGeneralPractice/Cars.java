package chap12ObjectOrientedGeneralPractice;

public class Cars {
    //汽车的三个属性
    private String brand;
    private String price;
    private String colour;
    //空参构造
    public Cars() {
    }
    //get和set方法
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

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
    //有参构造
    public Cars(String brand, String price, String colour) {
        this.brand = brand;
        this.price = price;
        this.colour = colour;

    }
}
