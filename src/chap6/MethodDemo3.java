package chap6;

public class MethodDemo3 {
    //带返回值的方法定义
    public static void main(String[] args){
        overturn(11.2,22.3,22.3,333.3);//直接调用
        double f=overturn(11.2,22.3,22.3,333.3);
        System.out.println(f);//赋值调用
        System.out.println(overturn(11.2,22.3,22.3,333.3));//输出调用
    }
    public static double overturn(double a,double b,double c,double d){
        double e=a+b+c+d;
        return e;
    }
}
//方法不调用就不执行
//方法之间是平行关系，不能互相嵌套定义
//方法的编写顺序与执行顺序无关
//方法返回值为void，表示该方法没有返回值，没有返回值的方法可以省略return不写，也可以写（表示结束方法），return语句下面不能写代码，因为执行不到
