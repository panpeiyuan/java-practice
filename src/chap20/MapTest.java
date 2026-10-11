package chap20;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;

public class MapTest {
    //核心：HashMap的键位置如果存储的是自定义对象，需要重写hashCode和equals方法(快捷键直接生成即可)
    public static void main(String[] args){
        HashMap<Student,String> hm=new HashMap<>();
        Student s1=new Student("zhangsan",22);
        Student s2=new Student("lisi",21);
        Student s3=new Student("wangwu",23);
        Student s4=new Student("wangwu",23);
        hm.put(s1,"jiangsu");
        hm.put(s2,"sichuan");
        hm.put(s3,"hebei");
        hm.put(s4,"shandong");
        //第一种遍历方式：把所有key放进一个set集合，再通过遍历set集合查询value
        Set<Student> key=hm.keySet();
        for (Student student : key) {
            System.out.println(student+"="+hm.get(student));
        }
        System.out.println("=======使用键值对遍历方式=========");
        //第二种遍历方式：获取到key和value整体（entry），再把entry对象放进set集合当中
        Set<Map.Entry<Student,String>> entries=hm.entrySet();
        for (Map.Entry<Student, String> entry : entries) {
            System.out.println(entry);
        }
        System.out.println("=======使用lambda表达式遍历========");
        //第三种遍历方式，用hm调用foreach方法
        hm.forEach(new BiConsumer<Student, String>() {
            @Override
            public void accept(Student student, String s) {
                System.out.println(student+"="+s);
            }
        });
    }
}
class Student{
    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int age) {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}