package chap6;

public class MethodTest4 {
    public static void main(String[] args){
        int[] arr={1,2,3,4,5};
        System.out.print(exit(arr,6));
    }
    public static boolean exit(int[] arr,int number){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==number){
                return true;
            }
        }
        return false;
    }
}
