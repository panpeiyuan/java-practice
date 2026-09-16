package chap2;

import java.util.Scanner;

public class IfTest3 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入您的票号:");
        int i =sc.nextInt();
        if(i>=1 & i<=100){
            if(i%2==0){
                System.out.print("您坐在右边");
            }else{
                System.out.print("您坐在右边");
            }
        }else{
            System.out.print("请输入正确的票号");
        }
    }
}
