package chap5;

public class ArrayTest {
    public static void main(String[] args){
         //遍历数组里的每一个元素，并求数组里面所有数字的和
        int[] arr={1,2,3,4,5};
        int num=0;
        for (int i = 0; i < arr.length; i++) {
            num+=arr[i];
        }
        System.out.print(num);
    }
}
