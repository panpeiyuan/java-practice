package chap1;

public class VariableDemo2 {
    //变量练习二
    public static void main(String[] args) {
        //定义变量：数据类型 变量名=数据值
        int a = 10;
        System.out.println(a);//10
        int b =20;
        System.out.println(b);//20
        System.out.println(a+b);//30
        //变量名不能重复
        //修改变量a的值
        a=50;
        System.out.println(a);
        //一条语句定义多个变量
        int d=100,e=200,f=300;
        System.out.println(d);
        System.out.println(e);
        //变量在使用之前必须要赋值,建议定义变量是直接赋值
    }
}
