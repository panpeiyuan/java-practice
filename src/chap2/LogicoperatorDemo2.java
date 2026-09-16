package chap2;

public class LogicoperatorDemo2 {
    public static void main(String[] args){
        //短路逻辑运算符具有短路效果
        //简单理解：当左边的表达式能确定最终的结果则运算符右边的表达式就不会再执行了
        int a=100;
        int b=200;
        boolean result = ++a<=5 && ++b<=10;//++a已经不符合了，右边直接不执行
        System.out.println(result);
        System.out.println(a);
        System.out.println(b);
    }
}
