package chap8;

public class GeneralPractice11 {
    //利用动态初始化创建二维数组
    public static void main(String[] args){
        int[][] arr=new int[3][5];
        arr[0][0]=10;
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println("");
        }
    }
}
