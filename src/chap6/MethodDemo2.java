package chap6;

public class MethodDemo2 {
    public static void main(String[] args){
        method(9,12);//方法在调用时，参数的数量和类型必须与方法定义中小括号里面的变量一一对应，否则报错
    }
    public static void method(int num1,int num2){
        int result=num1+num2;
        System.out.print(result);
    }
}
