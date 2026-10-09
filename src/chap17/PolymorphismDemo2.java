package chap17;

public class PolymorphismDemo2 {
    public static void main(String[] args){
        //多态形式调用成员变量,满足的规则是：编译看左边，运行也看左边（即编译和运行都看父类）
        AnimalsTest a=new dog();
        System.out.println(a.name);
        //多态形式调用成员方法：编译看左边，运行看右边（即编译看父类，运行看子类）
        //编译看左边：javac编译代码的时候，会看左边的父类中有没有这个方法，如果有编译成功，没有则编译失败
        //运行看右边：在用java运行代码时，实际上运行的是子类中的方法
        a.show();
    }
}
class AnimalsTest{
    String name="动物";
    public void show(){
        System.out.println("Animal......show");
    }
}
class dog extends AnimalsTest{
    String name="狗";
    @Override
    public void show(){
        System.out.println("dog......show");
    }
}
class cat extends AnimalsTest {
    String name="猫";
    @Override
    public void show(){
        System.out.println("cat......show");
    }
}
