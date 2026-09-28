package chap13;

public class Distance {
    public static double euclidean(double[] a,double[] b){
        double sum=0;
        for (int i = 0; i < a.length; i++) {
            double euclidean=(a[i]-b[i])*(a[i]-b[i]);
            sum+=euclidean;
        }
        return Math.sqrt(sum);
    }
    public static double manhattan(double[] a,double[] b){
        double sum=0;
        for (int i = 0; i < a.length; i++) {
            double diff=a[i]-b[i];
            sum+= Math.abs(diff);
        }
        return sum;
    }
}

