package chap3;

import java.util.Scanner;

public class HuiWenShuTest {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("请输入一个三位数:");
        int i=sc.nextInt();
        int temp=i;//用temp来存储i，因为while循环会改变i的值
        int num=0;
        while(i!=0){
            int x=i%10;
            i=i/10;//修改i值的办法
            num=num*10+x;
        }
        if(num==temp){
            System.out.print("是回文数");
        }else{
            System.out.print("不是回文数");
        }
    }

}

