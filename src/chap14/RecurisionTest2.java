package chap14;

import java.util.Scanner;

public class RecurisionTest2 {
    //猴子吃桃子问题
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入一个10以内的天数:");
        int tempt=sc.nextInt();
        int num=peach(tempt);
        System.out.println("第"+tempt+"天有"+num+"个桃子");
    }
    public static int peach(int day){
        if(day==10){
            return 1;
        }else if(day>=1&&day<=9){
            return (peach(day+1)+1)*2;
        }else{
            System.out.println("请输入正确的天数");
            return -1;
        }
    }
}
