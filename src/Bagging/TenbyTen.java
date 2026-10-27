package Bagging;

import KFold.KFoldEncapsulation;

import java.io.File;
import java.util.ArrayList;

public class TenbyTen {
    public static void main(String[] args) throws Exception {
        File file = new File("D:/算法学习测试数据集/CSV/iri.csv");
        //原样本列表
        ArrayList<BaggingSample> samplelist = CsvReader.csvreader(file);

        int k = 10;//k折
        int times = 10;//times次k折
        int[] treecounts=new int[]{3,5,10};
        //三个treecount，每个treecount都进行一次10x10折
        for (int treecount : treecounts) {
            double sum=0;//存10x10折总平均正确率
            double sumSquares=0;//存100个测试折准确率的平方和
            for (int t = 0; t < times; t++) {
                //第t次k折
                //统计每次十折测试集的正确率;
                double sumpercorr = 0;
                double avgpercorr=0;
                double[] correct=new double[k];
                //固定随机抽取样本的种子
                long baggingbaseseed = 20261027L;
                KFoldEncapsulation kf = new KFoldEncapsulation(samplelist.size(), k, baggingbaseseed+t);//这里的seed决定本次10折怎么划分样本
                for (int fold = 0; fold < k; fold++) {
                    int[] train = kf.trainIndex(fold);//训练集
                    int[] test = kf.testIndex(fold);//测试集

                    //根据训练集下标存入对应的样本
                    ArrayList<BaggingSample> trainsample = new ArrayList<>();
                    for (int i : train) {
                        trainsample.add(samplelist.get(i));
                    }
                    //根据测试集下标存入对应样本
                    ArrayList<BaggingSample> testsample = new ArrayList<>();
                    for (int i : test) {
                        testsample.add(samplelist.get(i));
                    }

                    //创建调用方法的对象
                    DataSet dataSet=new DataSet(trainsample);
                    C45DecisionTree treebuilder=new C45DecisionTree(dataSet);
                    ArrayList<Integer> avaliableattribute=new ArrayList<>();
                    for(int i=0;i<dataSet.getAttnumber();i++){
                        avaliableattribute.add(i);
                    }

                    //在训练集上训练BaggingClassifier,每折都重新创建baggingclassifier
                    long baggingseed=baggingbaseseed+t*10L+fold;//这里的seed决定本折bagging怎么进行bootstrap抽样
                    BaggingClassifier baggingClassifier=new BaggingClassifier(treecount,treebuilder,avaliableattribute,baggingseed);
                    baggingClassifier.train(trainsample);

                    //在测试集上调用predict
                    int correctnum=0;
                    for (BaggingSample sample : testsample) {
                        int prelabel=baggingClassifier.predict(sample);
                        int truelabel=sample.getLabel();
                        if(prelabel==truelabel){
                            correctnum++;
                        }
                    }
                    double percorrect=(double)correctnum/testsample.size();
                    //把每折的正确率存起来
                    correct[fold]=percorrect;
                    sumSquares+=percorrect*percorrect;
                }
                for (int i = 0; i < correct.length; i++) {
                    sumpercorr+=correct[i];
                }
                avgpercorr=sumpercorr/10;
                sum+=avgpercorr;
            }
            double mean=sum/times;
            double variance=sumSquares/(times*k)-mean*mean;
            double standardDeviation=Math.sqrt(Math.max(variance,0.0));
            System.out.println("treecount取"+treecount+"时，测试集10x10折的平均正确率为"+mean+"，标准差为"+standardDeviation);
        }
    }
}
