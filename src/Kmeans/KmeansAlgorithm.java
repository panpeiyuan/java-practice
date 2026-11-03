package Kmeans;

import java.util.ArrayList;
import java.util.Random;

public class KmeansAlgorithm {
    private DataSet dataSet;
    int k;//簇数
    int maxIterations;//最大训练轮数
    private long seed;
    double[][] centers;
    int[] clusterAssignments;
    double sse;//记录当前划分的平方误差
    int iterations;//记录算法执行了多少轮

    public KmeansAlgorithm(DataSet dataSet, int k, int maxIterations, long seed) {
        this.dataSet = dataSet;
        this.k = k;
        this.maxIterations = maxIterations;
        this.seed = seed;
        this.centers=new double[k][];
        this.clusterAssignments=new int[dataSet.getSamplelist().size()];
        for (int i = 0; i < clusterAssignments.length; i++) {
            clusterAssignments[i]=-1;
        }
        this.sse=0;
        this.iterations=0;
    }

    //初始化簇中心方法
    public void initializeCenter(ArrayList<KmeansSample> samplelist, int k){
        //初始化簇中心
        double[][] initializecenter=new double[k][dataSet.getAttnumber()];
        //为每个样本生成一个标记，只选取没有选过的样本作为中心
        int[] sampletag=new int[samplelist.size()];
        for (int i = 0; i < sampletag.length; i++) {
            sampletag[i]=0;
        }
        //创建随机对象
        Random random=new Random(seed);
        //选定k个不同的簇中心
        for (int i = 0; i < initializecenter.length; i++) {
            int randomindex=0;
            //只要抽到被选过的样本，就重新抽取，知道抽到未被选过的样本才会结束循环
            do{
                randomindex=random.nextInt(samplelist.size());
            }while(sampletag[randomindex]==1);
            //把选过的样本标记改为1
            sampletag[randomindex]=1;
            for (int j = 0; j < initializecenter[i].length; j++) {
                initializecenter[i][j]=samplelist.get(randomindex).getAttribute()[j];
            }
        }
        this.centers=initializecenter;
    }

    //簇划分方法
    public boolean assignClusters(ArrayList<KmeansSample> samplelist){
        //先假设该轮样本的簇分配没有改变
        boolean changed=false;
        //先遍历样本
        for (int i = 0; i < samplelist.size(); i++) {
            double mindistance=Double.POSITIVE_INFINITY;
            int mincenterindex=-1;
            double[] samplevalue=samplelist.get(i).getAttribute();
            //遍历所有中心
            for (int j = 0; j < centers.length; j++) {
                double[] centervalue=centers[j];
                //计算该样本与所有中心的距离
                double distance=Distance.squaredeuclidean(samplevalue,centervalue);
                if(distance<mindistance){
                    mindistance=distance;
                    mincenterindex=j;
                }
            }
            //按距离把第i个样本划分进簇
            if(mincenterindex!=clusterAssignments[i]){
                changed=true;
                clusterAssignments[i]=mincenterindex;
            }
        }
        return changed;
    }

    //更新簇中心方法
    public void updataCenters(ArrayList<KmeansSample> samplelist){
        //存放每个簇，所有样本每个属性的总和
        double[][] attributesum=new double[k][dataSet.getAttnumber()];
        //统计每个簇有多少个样本
        int[] samplenum=new int[k];

        //初始化
        for (int i = 0; i < attributesum.length; i++) {
            for (int j = 0; j < attributesum[i].length; j++) {
                attributesum[i][j]=0;
            }
        }
        for (int i = 0; i < samplenum.length; i++) {
            samplenum[i]=0;
        }
        //遍历所有样本
        for (int i = 0; i < samplelist.size(); i++) {
            //得到他们的簇编号
            int cluster=clusterAssignments[i];
            //把样本的属性取值加到对应的簇中
            double[] samplevalue=samplelist.get(i).getAttribute();
            for (int k = 0; k < attributesum[cluster].length; k++) {
                attributesum[cluster][k]+=samplevalue[k];
            }
            samplenum[cluster]++;
        }

        for (int i = 0; i < attributesum.length; i++) {
            if(samplenum[i]>0){
                for (int j = 0; j < attributesum[i].length; j++) {
                    centers[i][j]=attributesum[i][j]/samplenum[i];
                }
            }
        }
    }

    //计算sse方法
    public void SSE(ArrayList<KmeansSample> samplelist){
        double sumsse=0;
        for (int i = 0; i < samplelist.size(); i++) {
            int cluster=clusterAssignments[i];
            double[] clustercentervalue=centers[cluster];
            double[] samplevalue=samplelist.get(i).getAttribute();
            sumsse+=Distance.squaredeuclidean(samplevalue,clustercentervalue);
        }
        this.sse=sumsse;
    }

    //fit方法，根据上述方法对样本进行训练
    public void fit(ArrayList<KmeansSample> samplelist,int k){
        initializeCenter(samplelist,k);
        while(iterations<maxIterations){
            boolean changed=assignClusters(samplelist);
            updataCenters(samplelist);
            SSE(samplelist);
            iterations++;
            if(changed==false){
                break;
            }
        }
    }


}
