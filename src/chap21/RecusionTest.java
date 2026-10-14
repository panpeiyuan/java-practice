package chap21;

import java.util.Arrays;

public class RecusionTest {
    public static void main(String[] args){
        int[] arr=new int[]{1,1,4,7,5,2,6,9,0,4};
        fun(arr,arr.length-1);
        System.out.print(Arrays.toString(arr));
    }
    public static void fun(int[] arr,int n){
        if(n==0){
            return;
        }
        for (int i = 0; i <n; i++) {
            if (arr[i] > arr[i + 1]) {
                int tempt = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = tempt;
            }
        }
        fun(arr, n - 1);
    }
}
