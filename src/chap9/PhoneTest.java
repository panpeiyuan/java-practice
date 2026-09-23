package chap9;

public class PhoneTest {
    public static void main(String[] args){
        //创建手机的对象
        Phone p=new Phone();
        //给手机赋值
        p.brand="小米";
        p.price=1999;
        //获得手机对象中的值
        System.out.println(p.brand);
        System.out.println(p.price);
        //调用手机中的方法
        p.call();
        p.playGame();
        //创建第二个对象
        Phone p2=new Phone();
        //给第二个对象赋值
        System.out.println(p2.brand="苹果");
        System.out.println(p2.price=2000);
        //用第二个对象调用方法
        p2.call();
        p2.playGame();
    }
}
