package chap5;

public class ArrayDemo2 {
    public static void main(String[] args) {
        //利用索引对数组中的元素进行访问
        //1.获取，
        int[] arr = {1, 2, 3, 4};
        int number = arr[0];
        System.out.println(number);
        System.out.println(arr[1]);
//        2.存储
        arr[0]=100;
        System.out.print(arr[0]);//存储新值之后，原来的值就不存在了
    }

}
