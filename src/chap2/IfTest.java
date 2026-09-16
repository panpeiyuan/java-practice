package chap2;

import java.util.Scanner;

public class IfTest {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("请设置红绿灯颜色:");
        String i =sc.next();
        if (i.equals("绿色")){
            System.out.print("gogogo");
        }
        if (i.equals("黄色")){
            System.out.print("slow");
        }
        if (i.equals("红色")){
            System.out.print("stop");
        }
    }
}
