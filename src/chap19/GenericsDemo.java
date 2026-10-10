package chap19;

import java.util.ArrayList;
import java.util.Iterator;

public class GenericsDemo {
    public static void main(String[] args){
        //若没有给集合指定类型，则他会默认所有数据类型都是object，此时可以向集合中添加任意类型的数据，但是在获取数据时无法使用他的特有行为
        ArrayList<String> list=new ArrayList();
        //向集合中加入元素
        list.add("123");
        list.add("aaa");
        list.add("new student(zhangsan,22)");
        Iterator<String> it=list.iterator();//迭代器的泛型与集合的泛型相同
        while(it.hasNext()){
            String str=it.next();
            //多态的弊端是不能调用子类的特有功能
            System.out.println(str);
        }
    }
}
class student{
    private String name;
    private int age;

    @Override
    public String toString() {
        return "student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public student() {
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
}
