package chap17;

public class abstractTest {
    public static void main(String[] args){
        frog f=new frog("小娃娃",13);
        f.eat();
    }
}
abstract class animal{
    private String name;
    private int age;

    public animal() {
    }

    public animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public abstract void eat();
    public void drink(){
        System.out.println("正在喝水");
    }
}
class frog extends animal{
    public frog(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println("青蛙吃虫子");
    }
}
class dog1 extends animal{
    public dog1(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println("狗吃骨头");
    }
}
class sheep extends animal{
    public sheep(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println("山羊吃草");
    }
}