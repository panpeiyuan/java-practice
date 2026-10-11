package chap19;

import java.util.ArrayList;

public class UtilTest2 {
    public static void main(String[] args) {
        ArrayList<Student2> arr = new ArrayList<>();
        Student2 stu1 = new Student2("zhangsan", 22, "male");
        Student2 stu2 = new Student2("lisi", 23, "female");
        Student2 stu3 = new Student2("wangwu", 21, "male");
        arr.add(stu1);
        arr.add(stu2);
        arr.add(stu3);
        System.out.println(maxAge.getMaxAge(arr));
    }
}
class Student2{
    private String name;
    private int age;
    private String sex;

    public Student2(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public Student2() {
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
class maxAge{
    private maxAge() {};
    public static int getMaxAge(ArrayList<Student2> arr){
        int maxage=0;
        for (int i = 0; i < arr.size(); i++) {
            if(arr.get(i).getAge()>maxage){
                maxage=arr.get(i).getAge();
            }
        }
        return maxage;
    }
}
