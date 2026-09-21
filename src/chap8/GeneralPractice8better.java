package chap8;

import java.util.Random;

public class GeneralPractice8better {
    //优化思路：不进行抽取，而是将奖池随机打乱顺序然后直接输出奖池
    public static void main(String[] qrgs){
        int[] arr={2,588,888,1000,10000};
        Random r=new Random();
        for(int i=0;i<arr.length;i++){
            int randomindex=r.nextInt(arr.length);
            int temp=arr[i];
            arr[i]=arr[randomindex];
            arr[randomindex]=temp;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
