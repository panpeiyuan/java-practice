package chap7;

public class GeneralPractice4 {
    public static void main(String[] args){
        int[] i={1,2,3,4,5,6};
        int[] j=new int[6];
        for(int n=0;n<i.length;n++){
            j[n]=i[n];
        }
        for (int m = 0; m < j.length; m++) {
            System.out.print(j[m]);
        }

    }
}
