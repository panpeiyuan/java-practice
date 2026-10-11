package chap19;

public class UtilTest {
    public static void main(String[] args){
        int arr1[]=new int[]{1,2,3,4,5};
        System.out.println(ArrayUtil.printArr(arr1));
        double arr2[]=new double[]{1.1,2.2,3.3,4.4};
        System.out.println(ArrayUtil.getAverage(arr2));
    }
}
class ArrayUtil{
    private ArrayUtil() {};
    public static String printArr(int[] arr){
        String str="[";
        for (int i = 0; i < arr.length-1; i++) {
            str=str+arr[i]+",";
        }
        str+=arr[arr.length-1];
        str=str+"]";
        return str;
    }
    public static double getAverage(double[] arr){
        double sum_grade=0;
        for (int i = 0; i < arr.length; i++) {
            sum_grade+=arr[i];
        }
        double avg_grade=sum_grade/arr.length;
        return avg_grade;
    }
}
