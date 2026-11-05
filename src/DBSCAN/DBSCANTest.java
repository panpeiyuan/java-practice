package DBSCAN;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class DBSCANTest {
    public static void main(String[] args) throws Exception {
        //标准化数据处理流程
        File file=new File("D:/算法学习测试数据集/CSV/win.csv");
        ArrayList<DBSCANSample> samplelist=CsvReader.csvreader(file);
        Normalization normalmodel=new Normalization();
        normalmodel.fit(samplelist);
        ArrayList<DBSCANSample> normalsamples=normalmodel.apply(samplelist);
        DataSet dataSet=new DataSet(normalsamples);
        System.out.println("样本数:" + normalsamples.size());
        System.out.println("特征数:" + dataSet.getAttnumber());
        System.out.println("是否标准化:是");
        //参数规定与DBSCAN算法构建
        double eps=2.0;
        int minPts=5;
        DBSCANAlgorithm DESCANmodel=new DBSCANAlgorithm(dataSet,eps,minPts);
        DESCANmodel.fit();
        int[] clusterassign=DESCANmodel.getClusterAssignments();
        Map<Integer,Integer> clustersamplecount=new TreeMap<>();
        for (int i = 0; i < clusterassign.length; i++) {
            if(clustersamplecount.containsKey(clusterassign[i])){
                int clusternum=clustersamplecount.get(clusterassign[i]);
                clusternum++;
                clustersamplecount.put(clusterassign[i],clusternum);
            }else{
                clustersamplecount.put(clusterassign[i],1);
            }
        }
        for (Integer clusterindex : clustersamplecount.keySet()) {
            if(clusterindex==-2){
                System.out.println("噪声数为:"+clustersamplecount.get(clusterindex));
            }else{
                System.out.println("该簇编号为:"+clusterindex+"该簇样本数为:"+clustersamplecount.get(clusterindex));
            }
        }

    }
}
