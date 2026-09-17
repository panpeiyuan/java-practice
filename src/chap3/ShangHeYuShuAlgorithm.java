package chap3;

import java.util.Scanner;

public class ShangHeYuShuAlgorithm {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("请输入被除数:");
        int beichushu=sc.nextInt();
        System.out.print("请输入除数:");
        int chushu=sc.nextInt();
        int count=0;
        while(beichushu-chushu>=0){
            beichushu-=chushu;
            count++;
        }
        if(beichushu>0){
            System.out.println("商为:"+count);
            System.out.print("余数为:"+beichushu);
        }else{
            System.out.print("商为:"+count);
        }
    }
}
