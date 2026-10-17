package Bayes;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class BayesTest {
    public static void main(String[] args) throws Exception {
        File file=new File("D:/算法学习测试数据集/CSV/hay.csv");
        ArrayList<Bayessample> bayessamples=CsvReader.csvreader(file);
        DataSet ds=new DataSet(bayessamples);//创建一个dataset对象
        BayesAlgorithm bayes=new BayesAlgorithm(ds);//创建一个bayesalgorithm对象

        Map<Integer,Double> P_Yck=bayes.P_Yck(ds);//求出先验概率;

        Set<Integer> Yck=P_Yck.keySet();//取出所有类别的取值
        for (Integer i : Yck) {//遍历先验概率
            System.out.println(i+"类别的先验概率为:"+P_Yck.get(i));
        }

        Map<Integer,ArrayList<Map<Integer,Double>>> P_Xajl_Yck=bayes.P_Xajl_Yck(ds);
        for (Integer i : Yck) {
            System.out.println("类别:"+i);
            ArrayList<Map<Integer,Double>> attlist=P_Xajl_Yck.get(i);
            for(int j=0;j<attlist.size();j++){
                Map<Integer,Double> att=new TreeMap<>(attlist.get(j));
                double sum=0;
                for (Double value : att.values()) {
                    sum+=value;
                }
                System.out.println("属性"+j+":"+att+" sum:"+sum);
            }
        }

        bayes.train();//先训练

        int correct=0;//数预测正确的个数
        for (Bayessample bayessample : ds.getSamplelist()) {
            if(bayes.predict(bayessample)==bayessample.getLabel()){
                correct++;
            }
        }
        double correctpercent=(double)correct/ds.getSamplelist().size();
        System.out.println("全集样本预测预测正确率为:"+correctpercent);

    }
}
