package chap8;

import java.util.Random;

public class GeneralPractice8 {
        public static void main(String[] args){
            int[] arr={2,588,888,1000,10000};
            int[] arr1=new int[5];
            Random r=new Random();
            for (int i = 0; i < 5;) {
                int randomindex=r.nextInt(arr.length);
                int price=arr[randomindex];
                if(!contains(arr1,price)){
                    //把抽取到的奖项添加到arr1中
                    arr1[i]=price;
                    i++;//抽取到有效奖项时索引才会变化
                }
            }
            for (int i = 0; i < arr1.length; i++) {
                System.out.println(arr1[i]);
            }
        }
        public static boolean contains(int[] arr,int price){
            for (int i = 0; i < arr.length; i++) {
                if(arr[i]==price){
                    return true;
                }
            }
            return false;
        }
}
