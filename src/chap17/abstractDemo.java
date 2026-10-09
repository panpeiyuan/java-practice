package chap17;

public  class abstractDemo {
    public static void main(String[] args) {
        student1 s = new student1("张三",23);
        System.out.println(s.getName()+", "+s.getAge());
    }
}
abstract class person1{
    //抽象类不能创建对象
    //抽象类不一定有抽象方法，但是有抽象方法的类一定是抽象类
    //抽象类可以有构造方法(作用为，当创建子类对象时，给子类对象的属性进行赋值）
    //抽象类的子类：要么重写抽象类中的所有抽象方法，要么是抽象类
    private String name;
    private int age;

    public person1() {
    }

    public person1(String name, int age) {
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

    public abstract void work();

    public void sleep(){
        System.out.println("睡觉");
    }
}
class student1 extends person1{
    public student1() {
    }

    public student1(String name, int age) {
        super(name, age);//通过super关键字交给父类去进行赋值
    }

    @Override
    public void work() {
        System.out.println("学生的工作是学习");
    }
}
