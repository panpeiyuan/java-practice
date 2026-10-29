package AdaBoost;

import java.util.ArrayList;
import java.util.Arrays;

public class C45DecisionTReeStump {
    private DataSet dataSet;
    public C45DecisionTReeStump(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public TreeStumpNode train(ArrayList<AdaBoostSample> samplelist, double[] Weight) {
        DataSet dataSet1 = new DataSet(samplelist);
        int attributenum = dataSet1.getAttnumber();

        //保存一套最佳树桩参数
        double besterrorrate=Double.POSITIVE_INFINITY;
        int bestattributeindex=-1;
        double bestthreshold=0;
        int bestleftlabel=0;
        int bestrightlabel=0;
        for (int i = 0; i < dataSet1.getAttnumber(); i++) {
            double[] attributevalue = new double[samplelist.size()];
            //遍历所有样本的该属性取值，放入取值数组中
            for (int j = 0; j < samplelist.size(); j++) {
                attributevalue[j] = samplelist.get(j).getAttribute()[i];
            }
            //对数组进行升序排列
            Arrays.sort(attributevalue);
            //创建阈值列表
            ArrayList<Double> thresholdlist = new ArrayList<>();
            for (int j = 0; j < attributevalue.length - 1; j++) {
                if (attributevalue[j] == attributevalue[j + 1]) {
                    continue;
                }
                thresholdlist.add((attributevalue[j] + attributevalue[j + 1]) / 2);
            }

            //遍历每个阈值
            for (Double threshold : thresholdlist) {
                //第一种情况：大于等于threshold的取1，小于threshold的取-1
                double error1 = 0;
                for (int j = 0; j < samplelist.size(); j++) {
                    int prelabel = 0;
                    int truelabel = samplelist.get(j).getLabel();
                    if (samplelist.get(j).getAttribute()[i] >= threshold) {
                        prelabel = 1;
                    } else {
                        prelabel = -1;
                    }
                    //把预测错误的样本权重加和
                    if (prelabel != truelabel) {
                        error1 += Weight[j];
                    }
                }
                //归一化错误率
                double Weightsum = Arrays.stream(Weight).sum();
                double errorrate1=error1/Weightsum;
                if(errorrate1<besterrorrate){
                    besterrorrate=errorrate1;
                    bestattributeindex=i;
                    bestthreshold=threshold;
                    bestrightlabel=1;
                    bestleftlabel=-1;
                }
                //第二种情况：大于等于threshold的取-1，小于threshold的取1
                double error2=0;
                for (int j = 0; j < samplelist.size(); j++) {
                    int prelabel = 0;
                    int truelabel = samplelist.get(j).getLabel();
                    if (samplelist.get(j).getAttribute()[i] >= threshold) {
                        prelabel = -1;
                    } else {
                        prelabel = 1;
                    }
                    //把预测错误的样本权重加和
                    if (prelabel != truelabel) {
                        error2 += Weight[j];
                    }
                }
                //归一化错误率
                double errorrate2=error2/Weightsum;
                if(errorrate2<besterrorrate){
                    besterrorrate=errorrate2;
                    bestattributeindex=i;
                    bestthreshold=threshold;
                    bestrightlabel=-1;
                    bestleftlabel=1;
                }
            }
        }

        if(bestattributeindex==-1){
            return null;
        }
        TreeStumpNode treestump=new TreeStumpNode(bestattributeindex,bestthreshold,bestleftlabel,bestrightlabel);
        return treestump;
    }

    //预测方法，大于阈值返回右侧标签，小于阈值返回左侧标签
    public int predict(AdaBoostSample sample, TreeStumpNode treestump){
        int attvalueindex= treestump.getAttributeindex();
        double threshold=treestump.getThreshold();
        if(sample.getAttribute()[attvalueindex]>=threshold){
            return treestump.getRightlabel();
        }else{
            return treestump.getLeftlabel();
        }
    }
}
