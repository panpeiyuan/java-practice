package chap9;

public class Phone {
    //在创建对象之前，要先定义类（表示同一类事物）
    //属性
    String brand;//属性
    double price;//属性（只定义不给值）
    //行为
    public void call(){
        System.out.println("手机在打电话");
    }
    public void playGame(){
        System.out.println("手机在玩游戏");
    }
}
//属性都有默认的初始化值，数字都是0，布尔是false，类、接口、数组、String的默认初始值都是null