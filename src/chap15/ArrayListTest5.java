package chap15;

import java.util.ArrayList;

public class ArrayListTest5 {
    public static void main(String[] args){
        ArrayList<Phone> phone=new ArrayList<>();
        Phone p1=new Phone("小米",1000);
        Phone p2=new Phone("苹果",8000);
        Phone p3=new Phone("锤子",2999);
        phone.add(p1);
        phone.add(p2);
        phone.add(p3);
        for(int i=0;i<phone.size();i++){
            if(lowprice(phone.get(i).getPrice())){
                System.out.println(phone.get(i).getBrand()+phone.get(i).getPrice());
            }
        }
    }
    public static boolean lowprice(double price){
        if(price<3000){
            return true;
        }
        return false;
    }
}
class Phone{
    private String brand;
    private double price;

    public Phone() {
    }

    public Phone(String brand, double price) {
        this.brand = brand;
        this.price = price;
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
}
