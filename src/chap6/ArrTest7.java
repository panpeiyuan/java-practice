package chap6;

import java.util.Random;

public class ArrTest7 {
    public static void main(String[] args) {
        //把数组随机打乱
        int[] arr = {1, 2, 3, 4, 5};
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            int randomindex = r.nextInt(5);//获取随机索引
            int temp = arr[i];
            arr[i] = arr[randomindex];
            arr[randomindex] = temp;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
    }
}