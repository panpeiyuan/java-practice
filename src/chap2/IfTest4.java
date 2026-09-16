package chap2;

import java.util.Scanner;

public class IfTest4 {
    public static void main(String[] args){
        Scanner sc =new Scanner(System.in);
        System.out.print("请输入小明的考试成绩:");
        int i =sc.nextInt();
        if(i>=0&&i<=100) {
            if (i >= 95) {
                System.out.print("非常优秀");
            } else if (i >= 90) {
                System.out.print("不错不错");
            } else if (i >= 80) {
                System.out.print("还行还行");
            } else {
                System.out.print("还需努力");
            }
        } else{
            System.out.print("请输入正确的成绩格式");
        }
    }
}
