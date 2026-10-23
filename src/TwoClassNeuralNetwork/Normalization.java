package TwoClassNeuralNetwork;


import java.util.ArrayList;

public class Normalization {
    private double[] avgattvalue;
    private double[] sdattvalue;
    public void fit(ArrayList<NeuralnetSample> train){
        //算输入样本的均值和标准差
        DataSet ds=new DataSet(train);
        avgattvalue=new double[ds.getAttnumber()];
        sdattvalue=new double[ds.getAttnumber()];
        for (int index = 0; index < ds.getAttnumber(); index++) {
            double sumindex=0;
            double varindex=0;

            //遍历所有样本的每个属性取值，按位置加和取平均后存入sunattvalue数组
            for (NeuralnetSample trainsample : train) {
                sumindex+=trainsample.getvalue(index);
            }

            double avgvalue=sumindex/train.size();
            avgattvalue[index]=avgvalue;

            //根据平均值求标准差，存入属性标准差数组
            for (NeuralnetSample trainsample : train) {
                //根据平均值求方差
                varindex+=(trainsample.getvalue(index)-avgvalue)*(trainsample.getvalue(index)-avgvalue);
            }
            double sdindex=Math.sqrt(varindex/(train.size()-1));
            sdattvalue[index]=sdindex;
        }
    }

    public ArrayList<NeuralnetSample> apply(ArrayList<NeuralnetSample> originsamplelist){
        //根据训练样本的均值和标准差，对测试数据进行标准化
        ArrayList<NeuralnetSample> normalsamplelist=new ArrayList<>();
        DataSet ds=new DataSet(originsamplelist);

        for (NeuralnetSample originsample : originsamplelist) {
            double[] oriatt=originsample.getAttributevalue();
            double[] noratt=new double[ds.getAttnumber()];
            for (int index = 0; index < oriatt.length; index++) {
                noratt[index]=(oriatt[index]-avgattvalue[index])/sdattvalue[index];
            }
            NeuralnetSample norsample=new NeuralnetSample(noratt,originsample.getLabel());
            normalsamplelist.add(norsample);
        }
        return normalsamplelist;
    }
}
