package chap4;

import java.util.Scanner;

public class LoopTest3 {
    //求键盘输入的数是不是质数（质数：只能被1和本身整除，否则这个数叫做合数）
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入一个整数:");
        int i=sc.nextInt();
        for(int n=2;n<=i-1;n++){//简化思路：n循环到i的平方根即可
            if(i%n!=0){
                System.out.print(i+"是质数");
                break;
            }else{
                System.out.print(i+"不是质数");
                break;
            }
        }

    }
}
