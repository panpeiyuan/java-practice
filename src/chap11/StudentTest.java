package chap11;

public class StudentTest {
    public static void main(String[] args){
        //创建对象
        //调用空参构造方法(一般用于参数值需要键盘录入的时候)
        //不管用不用的到，在写代码的时候，一般有参和无参的构造都要写
//        Student s=new Student();
//        s.getAge();

        //调用有参构造方法
        Student s=new Student("zhangsan",23);
        System.out.println(s.getName());
        System.out.println(s.getAge());
    }
}
