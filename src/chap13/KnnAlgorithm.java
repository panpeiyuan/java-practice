package chap13;

public class KnnAlgorithm {
    private int[] trainlabels;
    private int k;
    private double[][] train;
    public KnnAlgorithm(double[][] train,int[] trainlabels,int k){
        this.train=train;
        this.trainlabels=trainlabels;
        this.k=k;
    }
    public  int predict(double[] test){
        double min_distance=Double.MAX_VALUE;
        int min_index=-1;
        for (int i = 0; i < train.length; i++) {
            double tempt_distance=Distance.euclidean(train[i],test);//train[][]是二维数组，train[i]是一维数组
            if(tempt_distance<min_distance){
                min_distance=tempt_distance;
                min_index=i;
            }
        }
        return trainlabels[min_index];
    }

}
