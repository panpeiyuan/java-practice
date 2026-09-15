package chap1;

public class ArithmeticoperatoroDemo {
    //结论：只有整数参与运算结果只是整数，若有小数参与运算则结果为小数
    public static void main(String[] args){
        //+
        System.out.println(3+1);
        //-
        System.out.println(3-1);
        //*
        System.out.println(33*2);
        //有小数参与运算时,结果有可能是不精确的
        System.out.println(1.1+1.1);
        System.out.println(1.1+1.01);
        //除法
        System.out.println(10/2);
        System.out.println(10/3.3);
        //取余,取模
        System.out.println(10%2);
        System.out.println(10%3);
    }
}
