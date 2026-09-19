package chap5;

public class ArrayDemo3 {
    public static void main(String[] args){
        int[] arr={1,2,3,4};
        //获取数组里面所有的元素
        System.out.print(arr.length);//JAva中关于数 组的一个长度属性，length;调用方式：数组名.length;
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
        //IDEA提供了一种快捷的遍历数组方法:arr.fori
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
