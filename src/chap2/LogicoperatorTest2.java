package chap2;

import java.util.Scanner;

public class LogicoperatorTest2 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("请输入第一个整数:");
        int a=sc.nextInt();
        System.out.print("请输入第二个整数:");
        int b=sc.nextInt();
        System.out.print("请输入第三个整数:");
        int c=sc.nextInt();
        int max_ab=a>=b?a:b;
        int max=max_ab>=c?max_ab:c;
        System.out.print(max);
    }
}
//ctrl+alt+L:自动格式化代码
