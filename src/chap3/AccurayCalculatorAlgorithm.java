package chap3;

import java.util.Scanner;

public class AccurayCalculatorAlgorithm {
    public static void main(String[] args){
        String t1="A";
        String t2="B";
        String t3="A";
        String t4="A";
        String t5="B";
        Scanner sc=new Scanner(System.in);
        System.out.print("请输入第一个预测值:");
        String i=sc.next();
        int n=0;
        if(i.equals(t1)){
            n++;
        }
        System.out.print("请输入第二个预测值:");
        String j=sc.next();
        if(j.equals(t2)){
            n++;
        }
        System.out.print("请输入第三个预测值:");
        String k=sc.next();
        if(k.equals(t3)){
            n++;
        }
        System.out.print("请输入第四个预测值:");
        String l=sc.next();
        if(l.equals(t4)){
            n++;
        }
        System.out.print("请输入第五个预测值:");
        String m=sc.next();
        if(m.equals(t5)){
            n++;
        }
        System.out.print("预测正确:"+n+"个"+"\t精度为:"+((n/5.0)*100)+"%");
    }
}
