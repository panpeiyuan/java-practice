package chap21;

import java.util.Scanner;

public class RecusionReview {
    //求斐波那契数列第n项的数
    public static void main(String[] args){
        System.out.println("请输入斐波那契数列的项数:");
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println("这一项对应的数字为:"+fun(n));
    }
    public static int fun(int n){
        if(n<=2){
            return 1;
        }
        return fun(n-2)+fun(n-1);
    }
}
