package chap6;

public class ArrTest6 {
    public static void main(String[] args){
        //数据对调
//        int a=10;
//        int b=20;
//        int temp=a;//数据对调需要引入第三方变量
//        a=b;
//        b=temp;
//        System.out.println(a);
//        System.out.println(b);
        int[] arr={1,2,3,4,5};
        for(int i=0,j=arr.length-1;i<j;i++,j--){
            int temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
    }
}
