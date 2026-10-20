package LogisticRegression;

import java.util.ArrayList;

public class Normalization {
    private double[] avgattvalue;
    private double[] sdattvalue;
    public void fit(ArrayList<LogisticSample> train){
        //算输入样本的均值和标准差
        DataSet ds=new DataSet(train);
        avgattvalue=new double[ds.getAttnumber()];
        sdattvalue=new double[ds.getAttnumber()];
        for (int index = 0; index < ds.getAttnumber(); index++) {
            double sumindex=0;
            double varindex=0;

            //遍历所有样本的每个属性取值，按位置加和取平均后存入sunattvalue数组
            for (LogisticSample trainsample : train) {
                sumindex+=trainsample.getvalue(index);
            }

            double avgvalue=sumindex/train.size();
            avgattvalue[index]=avgvalue;

            //根据平均值求标准差，存入属性标准差数组
            for (LogisticSample trainsample : train) {
                //根据平均值求方差
                varindex+=(trainsample.getvalue(index)-avgvalue)*(trainsample.getvalue(index)-avgvalue);
            }
            double sdindex=Math.sqrt(varindex/(train.size()-1));
            sdattvalue[index]=sdindex;
        }
    }

    public ArrayList<LogisticSample> apply(ArrayList<LogisticSample> originsamplelist){
        //根据训练样本的均值和标准差，对测试数据进行标准化
        ArrayList<LogisticSample> normalsamplelist=new ArrayList<>();
        DataSet ds=new DataSet(originsamplelist);

        for (LogisticSample originsample : originsamplelist) {
            double[] oriatt=originsample.getAttributevalue();
            double[] noratt=new double[ds.getAttnumber()];
            for (int index = 0; index < oriatt.length; index++) {
                noratt[index]=(oriatt[index]-avgattvalue[index])/sdattvalue[index];
            }
            LogisticSample norsample=new LogisticSample(noratt,originsample.getLabel());
            normalsamplelist.add(norsample);
        }
        return normalsamplelist;
    }


    public ArrayList<LogisticSample> normalsamplelist(ArrayList<LogisticSample> orisamplelist){
       fit(orisamplelist);
       return apply(orisamplelist);
    }
}
