package AdaBoost;

import Bagging.BaggingSample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class C45DecisionTReeStump {
    private DataSet dataSet;
    public C45DecisionTReeStump(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public C45DecisionTreeStumpNode train(ArrayList<AdaBoostSample> samplelist,double[] Weight){
        DataSet dataSet1=new DataSet(samplelist);
        int attributenum=dataSet1.getAttnumber();

        //创建Map<属性，阈值>
        Map<Integer,double[]> map_att_threshold=new HashMap<>();
        //遍历所有属性
        for(int i=0;i<attributenum;i++){
            double[] threshold=new double[samplelist.size()-1];//一个属性建一个阈值数组
            //取两个样本的中间值为阈值
            for (int j = 0; j < samplelist.size()-1; j++) {
                threshold[j]=(samplelist.get(j).getAttribute()[i]+samplelist.get(j).getAttribute()[j+1])/2;
            }
            map_att_threshold.put(i,threshold);
        }
        //创建左右子树
        ArrayList<AdaBoostSample> leftsample=new ArrayList<>();
        ArrayList<AdaBoostSample> rightsample=new ArrayList<>();

        //遍历属性-阈值数值对
        for (Integer attribute : map_att_threshold.keySet()) {
            double[] thresholds=map_att_threshold.get(attribute);
            for (int i = 0; i < thresholds.length; i++) {
                for (AdaBoostSample adaBoostSample : samplelist) {
                    if(adaBoostSample.getAttribute()[attribute]<=thresholds[i]){
                        leftsample.add(adaBoostSample);
                    }else{
                        rightsample.add(adaBoostSample);
                    }
                }
                int error=0;
                double errorrate=0;
                for (AdaBoostSample leftsamples : leftsample) {
                    if(leftsamples.getLabel()==-1){
                        error+=;
                    }
                }
                for (AdaBoostSample rightsamples : rightsample) {
                    if(rightsamples.getLabel()==1){
                        error+=;
                    }
                }
                errorrate=error/samplelist.size();
            }
        }

    }

}
