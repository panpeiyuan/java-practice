package chap2;

import java.util.Scanner;

public class IfTest2 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("请输入你有多少钱:");
        int i=sc.nextInt();
        if(i>=100){
            System.out.print("吃点好的");
        }else{
            System.out.print("简单吃点");
        }

    }
}
