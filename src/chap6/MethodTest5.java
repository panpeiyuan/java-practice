package chap6;

public class MethodTest5 {
    public static void main(String[] args){
        int[] arr={1,2,3,4,5,6,7,8,9};
        System.out.print("[");
        int[] arr1=copyOfRange(arr,3,7);
        for (int i = 0; i < arr1.length; i++) {
            if(i==arr1.length-1){
                System.out.print(arr1[i]);
            }else{
                System.out.print(arr1[i]+",");
            }
        }
        System.out.print("]");
    }
    public static int[] copyOfRange(int[] arr,int from,int to){
        int[] arr_new=new int[to-from];
        int j=0;
        for(int i=from;i<to;i++){
            arr_new[j]=arr[i];
            j++;
        }
        return arr_new;
    }
}
