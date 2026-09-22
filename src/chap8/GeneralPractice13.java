package chap8;

public class GeneralPractice13 {
    public static void main(String[] args){
        int[][] arr={{22,66,44},{77,33,88},{25,45,65},{11,66,99}};
        int tempt=0;
        int count=0;
        for (int i = 0; i < arr.length; i++) {
            for(int j=0;j<arr[i].length;j++){
                count+=arr[i][j];
            }
            tempt+=count;
            System.out.println("第"+(i+1)+"个季度的营业额为"+count);
            count=0;
        }
        System.out.println("总营业额为:"+tempt);
    }
}
