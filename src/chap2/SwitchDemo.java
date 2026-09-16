package chap2;

import java.util.Scanner;

public class SwitchDemo {
    public static void main(String[] args){
        Scanner sc =new Scanner(System.in);
        System.out.print("请输入您选择的运动项目:");
        int i=sc.nextInt();
        switch(i){
            case 1:
                System.out.print("跑步");
                break;//不写break会导致case穿透，即达到符合要求的case之后依旧往下输出
            case 2:
                System.out.print("篮球");
                break;
            case 3:
                System.out.print("游泳");
                break;
            default://default可以省略，语法没有问题，位置也不一定放在最后，可以省略
                System.out.print("没有这个运动");
                break;
        }
    }
}
