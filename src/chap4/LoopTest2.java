package chap4;

import java.util.Scanner;

public class LoopTest2 {
    public static void main(String[] args){
         Scanner sc=new Scanner(System.in);
         System.out.print("请输入一个数字:");
         int i=sc.nextInt();
         for(int n=1;n<=i;n++){
             if(n*n<=i && (n+1)*(n+1)>i){
                 System.out.println(i+"的平方根的整数部分为"+n);
             }
         }
    }
}
