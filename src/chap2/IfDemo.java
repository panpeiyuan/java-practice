package chap2;

import java.util.Scanner;

public class IfDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入你的酒量:");
        int i = sc.nextInt();
        if (i >= 90) {//对boolean类型变量进行判断时，直接if（）即可，不用加判断条件，因为boolean类型变量只有true和false值
            System.out.print("小伙子不错");
        }
    }
}