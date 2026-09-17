package chap3;

import java.util.Scanner;

public class ForTest2 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("请输入起始值:");
        int i=sc.nextInt();
        System.out.print("请输入终止值:");
        int u=sc.nextInt();
        int count=0;
        for(int n=i;n<=u;n++){
            if(n%3==0&&n%5==0){
                count++;
            }
        }
        System.out.print("既能被3整除，又能被5整除的数有"+count+"个");
    }
}
