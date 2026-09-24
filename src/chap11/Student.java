package chap11;

public class Student {
    private String name;
    private int age;
    //如果没有写任何构造方法，虚拟机会自己构造一个空参构造方法
    public Student(){
        System.out.println("这是空参构造，不能直接给变量赋值，需要调用set方法");
    }

    public Student(String name,int age){//有参构造，通过这个构造对象可以直接给对象赋值
        this.name=name;
        this.age=age;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public int getAge(){
        return age;
    }

    public void setAge(){
        this.age=age;
    }
}
