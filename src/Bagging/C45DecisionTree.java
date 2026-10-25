package Bagging;

import java.util.ArrayList;
import java.util.Map;

public class C45DecisionTree {
    private DataSet dataSet;
    public C45DecisionTree(DataSet dataSet){
        this.dataSet=dataSet;
    }

    //计算样本信息熵的方法
    public double entropy(ArrayList<BaggingSample> samplelist){
        Map<Integer,Integer> labelcount=dataSet.getLabelscount();
        double entropy=0;
        for (Integer label : labelcount.keySet()) {
            entropy+=(labelcount.get(label)/samplelist.size())*(Math.log(labelcount.get(label)/samplelist.size())/Math.log(2));
        }
        return entropy;
    }

    //计算连续取值的样本的信息增益方法
    public double gain(ArrayList<BaggingSample> samplelist,int attributeindex){
        //根据候选的划分点进行划分，选择信息增益最大的
    }
}
