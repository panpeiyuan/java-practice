package chap2;

import java.util.Scanner;

public class LogicoperatorTest {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入第一个整数:");
        int i =sc.nextInt();
        System.out.print("请输入第二个整数:");
        int n =sc.nextInt();
        boolean result= i==6 || n==6 ||(i+n)%6==0;
        System.out.print(result);
    }
}
