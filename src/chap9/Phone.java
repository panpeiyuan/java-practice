package chap9;

public class Phone {
    //在创建对象之前，要先定义类（表示同一类事物）
    String brand;//属性
    double price;//属性（只定义不给值）
    //行为
    public void call(){
        System.out.print("手机在打电话");
    }
    public void playGame(){
        System.out.print("手机在玩游戏");
    }
}
