package chap15;

public class RecursionTest5 {
    //八皇后
    public static void main(String[] args){
        int[][] arr=new int [8][8];
        canplace(arr,0);
    }
    public static void canplace(int[][] arr,int row){
        if(row==arr.length){
            for (int i = 0; i < arr.length; i++) {
                for(int j=0;j<arr[i].length;j++){
                    System.out.print(arr[i][j]);
                }
                System.out.println();
            }
            return;
        }
        for(int line=0;line<arr[row].length;line++){
            boolean exists=ok(arr,row,line);
            if(exists){
                arr[row][line]=1;
                canplace(arr,row+1);
                arr[row][line]=0;//在每一次摆完之后把棋盘还原
            }
        }
    }
    public static boolean ok(int[][] arr,int row,int line){
        //判断这个格子的同一列和左上右上对角线是否有皇后
        for(int i=0;i<arr.length;i++){
            if(arr[i][line]==1){
                return false;
            }
        }
        for(int i=1;i<arr[line].length&&row-i>=0&&line-i>=0;i++){
            if(arr[row-i][line-i]==1){
                return false;
            }
        }
        for(int i=1;i<arr[line].length&&row-i>=0&&line+i<arr[row].length;i++){
            if(arr[row-i][line+i]==1){
                return false;
            }
        }
        return true;
    }
}
