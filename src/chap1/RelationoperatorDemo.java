package chap1;
import java.util.Scanner;

public class RelationoperatorDemo {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("请输入您衣服的时髦度:");
        int i=sc.nextInt();
        System.out.print("请输入您约会对象衣服的时髦度:");
        int m=sc.nextInt();
        System.out.print(i>m);
    }
}