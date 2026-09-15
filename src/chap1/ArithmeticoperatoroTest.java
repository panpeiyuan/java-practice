package chap1;

import java.util.Scanner;

public class ArithmeticoperatoroTest {
    public static void main(String[] args){
        System.out.println("请输入一个三位数");
        Scanner sc=new Scanner(System.in);
        int i=sc.nextInt();
        System.out.println("个位是:"+i%10);
        System.out.println("十位是:"+i/10%10);
        System.out.print("百位是:"+i/100%10);
    }
}
