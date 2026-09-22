package chap8;

public class GeneralPractice10 {
    public static void main(String[] args){
        //二维数组的静态初始化:int[][] arr=new int[][]{{11,22},{33,44}};
        //二维数组的动态初始化:int[][] arr={{11,23},{33,44}};
        int[][] arr=new int[][]{{1,2,3},{4,5,6,7,8}};
        int[][] arr1={{1,2,3},{4,5,6,7,8}};
        int[][] arr2={
                {1,2,3},
                {4,5,6,7,8}
        };
//        System.out.println(arr2[0]);//获取二维数组中的第一个一维数组
//        System.out.println(arr2[0][0]);
//        System.out.println(arr2[1][4]);
        //二维数组的遍历
        for (int i = 0; i < arr2.length; i++) {
            for(int j=0;j<arr2[i].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println("");
        }
    }
}
