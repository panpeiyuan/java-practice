package chap2;

import java.util.Scanner;

public class LogicoperatorDemo {
    public static void main(String[] args){
        //三元运算符：关系表达式？表达式1：表达式2
        //若关系运算符为真则输出表达式1，若关系运算符为假则输出表达式2
        Scanner sc=new Scanner(System.in);
        System.out.print("请输入第一个整数:");
        int a=sc.nextInt();
        System.out.print("请输入第二个整数:");
        int b=sc.nextInt();
        System.out.print(a==b?"相同":"不同");
    }
}
