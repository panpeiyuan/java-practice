package chap7;

import java.util.Scanner;

public class GeneralPractice5 {
    public static void main(String[] args){
        double[] score=new double[6];
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<score.length;i++){
            System.out.println("请输入第"+(i+1)+"个评委的评分:");
            double j=sc.nextDouble();
            score[i]=j;
        }
        double max=score[0];
        double min=score[0];
        for (int i = 0; i < score.length; i++) {
            if(score[i]>=max){
                max=score[i];
            }
        }
        for (int i = 0; i < score.length; i++) {
            if(score[i]<=min){
                min=score[i];
            }
        }
        double sum=0;
        for (int i = 0; i < score.length; i++) {
            sum+=score[i];
        }
        System.out.print("平均分为:"+(sum-max-min)/(score.length-2));
    }
}
