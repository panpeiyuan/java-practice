package chap17;

public class PolymorphismDemo {
    public static void main(String[] args){
        student s=new student();
        s.setName("潘培源");
        s.setAge(22);

        teacher t=new teacher();
        t.setName("曹瀚文");
        t.setAge(23);

        administructor a=new administructor();
        a.setName("张三");
        a.setAge(50);

        register(s);
        register(t);
        register(a);
    }
    public static void register(person p){//当方法的参数是类时，可以传递该类的所有子类对象
        p.show();
    }
}
class person{
    private String name;
    private int age;

    public person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public person() {
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

    public void show(){
        System.out.println(name);
        System.out.println(age);
    }
}
class student extends person{
    @Override
    public void show(){
        System.out.println("学生的信息为:"+getName()+","+getAge());
    }
}
class teacher extends person{
    @Override
    public void show(){
        System.out.println("老师的信息为:"+getName()+","+getAge());
    }
}
class administructor extends person{
    @Override
    public void show(){
        System.out.println("管理员信息为:"+getName()+","+getAge());
    }
}
