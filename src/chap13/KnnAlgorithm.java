package chap13;

public class KnnAlgorithm {
    private int[] trainlabels;
    private int k;
    private double[][] train;
    private int maxlabel;
    public KnnAlgorithm(double[][] train,int[] trainlabels,int k){
        this.train=train;
        this.trainlabels=trainlabels;
        this.k=k;
        maxlabel=0;
        for (int i = 0; i < trainlabels.length; i++) {
            if(trainlabels[i]>maxlabel){
                maxlabel=trainlabels[i];
            }
        }
    }
    public  int predict(double[] test){
        double[] alldistance=new double[train.length];
        for (int i = 0; i < train.length; i++) {
            double tempt_distance=Distance.euclidean(train[i],test);//train[][]是二维数组，train[i]是一维数组
            alldistance[i]=tempt_distance;
        }
        boolean[] used=new boolean[alldistance.length];
        int[] idx=new int[k];//记录最近的k个测试集数据的标签
        for(int i=0;i<k;i++){
            double min_distance=Double.MAX_VALUE;
            int min_distance_index=-1;
            for(int j=0;j<alldistance.length;j++) {
                if (!used[j] && alldistance[j] < min_distance) {
                    min_distance = alldistance[j];
                    min_distance_index = j;
                }
            }
            idx[i]=min_distance_index;
            used[min_distance_index]=true;
        }
        int[] vote = new int[maxlabel+1];
        for (int r = 0; r < k; r++) {
            vote[trainlabels[idx[r]]]++;
        }
        int maxvote=0;
        int maxvoteindex=-1;
        for (int i = 1; i < vote.length; i++) {
            if(vote[i]>maxvote){
                maxvote=vote[i];
                maxvoteindex=i;
            }
        }
        return maxvoteindex;
    }

}
