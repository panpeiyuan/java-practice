package chap14;

import java.util.Scanner;

public class RecursionTest {
    //求斐波那契数列第n项的数为几
    public  static int method(int n){
        if(n<=2){
            return 1;
        }else{
            return method(n-1)+method(n-2);
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入第n个数为:");
        int tempt=sc.nextInt();
        int num=method(tempt);
        System.out.println("第"+tempt+"个数为:"+num);
    }
}
