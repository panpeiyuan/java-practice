package chap14;

import java.util.Scanner;

public class RecursionDemo2 {
    //斐波那契数列练习
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入项数：");
        int n=sc.nextInt();
        int num=method(n);
        System.out.println("斐波那契数列第"+n+"项为:"+num);
    }
    public static int method(int n){
        if(n<=2){
            return 1;
        }
        return method(n-2)+method(n-1);
    }
}
