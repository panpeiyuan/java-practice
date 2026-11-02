package Kmeans;

import java.util.ArrayList;
import java.util.Random;

public class KmeansAlgorithm {
    private DataSet dataSet;
    int k;//簇数
    int maxIterations;//最大训练轮数
    double[][] centers;//k个簇中心的属性值
    int[] clustersample;//表示每个样本属于哪个簇
    double sse;//保存当前划分的平方误差
    int count;//记录算法执行了多少轮
    private long seed;

    public KmeansAlgorithm(DataSet dataSet, int k, int maxIterations,long seed) {
        this.dataSet = dataSet;
        this.k = k;
        this.maxIterations = maxIterations;
        this.seed=seed;
        centers=new double[k][dataSet.getAttnumber()];//k个簇中心及其属性
        //初始化划分结果，统一设置为-1
        clustersample=new int[dataSet.getSamplelist().size()];
        for (int i = 0; i < clustersample.length; i++) {
            clustersample[i]=-1;
        }
        sse=0;
        count=0;
    }

    public double[][] initializeCenter(ArrayList<KmeansSample> samplelist,int k){
        //初始化簇中心
        double[][] initializecenter=new double[k][dataSet.getAttnumber()];
        //为每个样本生成一个标记，只选取没有选过的样本作为中心
        int[] sampletag=new int[samplelist.size()];
        for (int sample = 0; sample < initializecenter.length; sample++) {
            for (int attribute = 0; attribute < initializecenter[sample].length; attribute++) {
                Random random=new Random(seed);
                int index=random.nextInt(samplelist.size());
                initializecenter[sample]=samplelist.get(index).getAttribute();
            }
        }
    }
}
