package Kmeans;

public class Distance {
    public static double squaredeuclidean(double[] a,double[] b){
        double sum=0;
        for (int i = 0; i < a.length; i++) {
            double euclidean=(a[i]-b[i])*(a[i]-b[i]);
            sum+=euclidean;
        }
        return sum;
    }
}
