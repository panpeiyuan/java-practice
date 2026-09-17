package chap3;

import java.util.Scanner;

public class ConfusionMatrix {
    public static void main(String[] args){
        String t1="A";
        String t2="B";
        String t3="A";
        String t4="A";
        String t5="B";
        int TP=0;
        int TN=0;
        int FP=0;
        int FN=0;
        System.out.println("前提：A为正例，B为反例");
        Scanner sc=new Scanner(System.in);
        System.out.print("请输入第一个预测值:");
        String p1=sc.next();
        System.out.print("请输入第二个预测值:");
        String p2=sc.next();
        System.out.print("请输入第三个预测值:");
        String p3=sc.next();
        System.out.print("请输入第四个预测值:");
        String p4=sc.next();
        System.out.print("请输入第五个预测值:");
        String p5=sc.next();
        if(p1.equals(t1)){
            TP++;
        }else{
            FN++;
        }
        if(p2.equals(t2)){
            TN++;
        }else{
            FP++;
        }
        if(p3.equals(t3)){
            TP++;
        }else{
            FN++;
        }
        if(p4.equals(t4)){
            TP++;
        }else{
            FN++;
        }
        if(p5.equals(t5)){
            TN++;
        }else{
            FP++;
        }
        System.out.println("TP="+TP+"\nFP="+FP+"\nTN="+TN+"\nFN="+FN);
        System.out.println("查准率:"+(TP*1.0/(TP+FP)));
        System.out.println("查全率:"+(TP*1.0/(TP+FN)));
        System.out.println("准确率:"+((TP*1.0+TN)/(TP+FP+TN+FN)));
    }
}
