package chap6;

import java.util.Random;

public class ArrTest5 {
    public static void main(String[] args){
        Random r=new Random();
        double[] arr=new double[10];
        for(int i=0;i<10;i++){
            arr[i]=r.nextDouble(100);
        }
        double sum=0;
        int count=0;
        for (int i = 0; i < arr.length; i++) {
            sum+=arr[i];
        }
        double avg=sum/arr.length;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]<avg){
                count++;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        System.out.println("和为:"+sum);
        System.out.println("平均数为:"+avg);
        System.out.print("有"+count+"个数比平均数小");
    }

}
