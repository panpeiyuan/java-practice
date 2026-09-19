package chap5;

public class ArrarDemo4 {
    public static void main(String[] args){
        //动态初始化数组
        String[] arr=new String[50];
        arr[0]="zhangsan";
        arr[1]="lisi";
        System.out.print(arr[0]);
        System.out.print(arr[1]);
        System.out.print(arr[2]);//String属于引用数据类型，默认初始化值为null
    }
}
