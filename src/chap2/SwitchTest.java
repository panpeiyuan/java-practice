package chap2;

import java.util.Scanner;

public class SwitchTest {
    public static void main(String[] args){
        Scanner sc =new Scanner(System.in);
        System.out.print("请输入今天是星期几:");
        int i=sc.nextInt();
        switch(i){
            case 1,2,3,4,5->System.out.print("工作日");
            case 6,7->System.out.print("休息日");
            default->System.out.print("请输入正确的星期数");
        }
    }
}
