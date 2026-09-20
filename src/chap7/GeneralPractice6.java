package chap7;

import java.util.Scanner;

public class GeneralPractice6 {
    public static void main(String[] args){
        //测输入的密码长度来确定数组长度，不要上来直接定义数组
        Scanner sc=new Scanner(System.in);
        System.out.print("请输入一组密码:");
        int i=sc.nextInt();
        int temp=i;
        int number=0;
        while(i!=0){
            i=(i-i%10)/10;
            number++;
        }
        int[] arr=new int[number];
        while(temp!=0){
            for(int n=0;n<arr.length;n++){
                arr[n]=temp%10;
                temp=(temp-temp%10)/10;
            }
        }
        System.out.print("加密后的密码为:");
        for (int i1 = 0; i1 < arr.length; i1++) {
            System.out.print((arr[i1]+5)%10);

        }

    }
}
