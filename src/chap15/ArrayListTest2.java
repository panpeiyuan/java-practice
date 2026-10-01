package chap15;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListTest2 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        ArrayList<student> students=new ArrayList<>();
        for(int i=0;i<3;i++) {
            student stu = new student();
            System.out.println("请输入第" + (i + 1) + "个学生的姓名:");
            stu.setName(sc.next());
            System.out.println("请输入第" + (i + 1) + "个学生的年龄:");
            stu.setAge(sc.nextInt());
            students.add(stu);
        }
        for (int i = 0; i < students.size(); i++) {
            System.out.println("第"+(i+1)+"个学生的姓名为:"+students.get(i).getName()+"，他的年龄是:"+students.get(i).getAge());
        }
    }
}
class student{
    private String name;
    private int age;

    public student() {
    }

    public student(String name, int age) {
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
}

