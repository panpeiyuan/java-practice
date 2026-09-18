package chap4;

import java.util.Random;
import java.util.Scanner;

public class LoopTest5 {
    public static void main(String[] args){
        Random r=new Random();
        int number =r.nextInt(100)+1;
        Scanner sc=new Scanner(System.in);
        System.out.print("请输入一个整数:");
        int i=sc.nextInt();
        while(i!=number){
            if(i<number){
                System.out.print("输小了，再输一次:");
                int m=sc.nextInt();
                i=m;
            }else if(i>number){
                System.out.print("输大了，再输一次:");
                int m=sc.nextInt();
                i=m;
            }
        }
        System.out.print("正确！");
    }
}
