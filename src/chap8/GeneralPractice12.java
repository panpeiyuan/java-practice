package chap8;

public class GeneralPractice12 {
    public static void main(String[] args){
        //特殊方法创建二维数组
        int[][] arr=new int[2][];
        int[] arr1={11,22};
        int[] arr2={33,44};
        arr[0]=arr1;
        arr[1]=arr2;
    }
}
