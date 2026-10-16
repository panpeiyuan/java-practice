package Bayes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BayesAlgorithm {
    private DataSet dataSet;

    public BayesAlgorithm(DataSet dataSet) {
        this.dataSet = dataSet;
    }


    public ArrayList<Double> P_Yck(DataSet ds){
        //计算先验概率P(y=ck),所有类别的先验概率存在一个数组里
        ArrayList<Double> P_Yck=new ArrayList<>();
        Set<Map.Entry<Integer,Integer>> labelentry=ds.getLabelscount().entrySet();
        for (Map.Entry<Integer, Integer> Entry : labelentry) {
            double p_yck=(double)Entry.getValue()/ds.size();
            P_Yck.add(p_yck);
        }
        return P_Yck;
    }

    public ArrayList<Set<Double>> P_Xajl_Yck(DataSet ds){
        Map<Integer,ArrayList<Double[]>> P_Xail_Yck=new HashMap<>();
        Set<Map.Entry<Integer,Integer>> labelentry=ds.getLabelscount().entrySet();
        for (Map.Entry<Integer, Integer> Entry : labelentry) {//遍历y的每一个取值和对应的个数
            for (Bayessample sample : ds.getSamplelist()) {

            }

        }
    }
}
