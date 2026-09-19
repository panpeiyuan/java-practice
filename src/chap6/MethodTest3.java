package chap6;

public class MethodTest3 {
    public static void main(String[] args){
        double[] arr={1.2,3.2,3.4,6.5,4.4,6.7};
        double max=max_arr(arr);
        System.out.print(max);
    }
    public static  double max_arr(double[] arr){
        double num=arr[0];
        for(int i=0;i<=arr.length-1;i++){
            if(arr[i]>=num){
                num=arr[i];
            }
        }
        return(num);
    }
}
