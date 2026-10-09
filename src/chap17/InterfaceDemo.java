package chap17;

public class InterfaceDemo {
    public static void main(String[] args){
        frog1 fg=new frog1("小娃娃",22);
        fg.eat();
        fg.Swim();
        System.out.println(fg.getAge());
        System.out.println(fg.getName());
        rubbit r=new rubbit("小兔兔",22);
        r.eat();
        System.out.println(r.getAge());
        System.out.println(r.getName());
    }
}
abstract class animals{
    private String name;
    private int age;

    public animals(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public animals() {
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
}
class frog1 extends animals implements Swim{
    @Override
    public void eat(){
        System.out.println("青蛙吃虫子");
    }
    @Override
    public void Swim(){
    System.out.println("青蛙在蛙泳");
    }

    public frog1(String name, int age) {
        super(name, age);
    }

    public frog1() {
    }
}
class dog2 extends animals implements Swim{
    @Override
    public void eat(){
        System.out.println("狗吃骨头");
    }
    @Override
    public void Swim(){
        System.out.println("狗在狗刨");
    }

    public dog2(String name, int age) {
        super(name, age);
    }

    public dog2() {
    }
}
class rubbit extends animals{
    @Override
    public void eat(){
        System.out.println("兔子吃胡萝卜");
    }

    public rubbit(String name, int age) {
        super(name, age);
    }

    public rubbit() {
    }
}
