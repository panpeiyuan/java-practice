package Kmeans;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class KmeansTest {
    public static void main(String[] args) throws Exception {
        File file=new File("D:/算法学习测试数据集/CSV/win.csv");
        ArrayList<KmeansSample> samplelist=CsvReader.csvreader(file);
        //对数据进行标准化后再聚类
        Normalization normalization=new Normalization();
        normalization.fit(samplelist);
        ArrayList<KmeansSample> normalizedSamples=normalization.apply(samplelist);
        DataSet dataSet=new DataSet(normalizedSamples);
        int k=3;
        int maxiterations=300;
        long seed=20261103L;
        System.out.println("样本数:" + normalizedSamples.size());
        System.out.println("特征数:" + dataSet.getAttnumber());
        System.out.println("是否标准化:是");

        KmeansAlgorithm kmeansmodel=new KmeansAlgorithm(dataSet,k,maxiterations,seed);
        kmeansmodel.fit(normalizedSamples,k);
        System.out.println("迭代次数为:"+kmeansmodel.iterations);
        System.out.println("SSE:" + kmeansmodel.sse);

        for (int i = 0; i < kmeansmodel.centers.length; i++) {
            System.out.println("中心" + (i + 1) + ":" + Arrays.toString(kmeansmodel.centers[i]));
        }

        int[] clusterCounts = new int[k];
        for (int cluster : kmeansmodel.clusterAssignments) {
            clusterCounts[cluster]++;
        }

        int total = 0;
        for (int i = 0; i < clusterCounts.length; i++) {
            System.out.println("簇" + (i + 1) + "样本数:" + clusterCounts[i]);
            total += clusterCounts[i];
        }
        System.out.println("簇样本数合计:" + total);
    }
}
