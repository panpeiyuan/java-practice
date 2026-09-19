package chap6;

public class ArrTest4 {
    public static void main(String[] args){
        int[] arr={33,5,22,44,55};
        int num=arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(arr[i]>=num){
                num=arr[i];
            }
        }
        System.out.print(num);
    }
}
