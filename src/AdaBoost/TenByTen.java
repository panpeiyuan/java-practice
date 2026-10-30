package AdaBoost;

import KFold.KFoldEncapsulation;

import java.io.File;
import java.util.ArrayList;

public class TenByTen {
    public static void main(String[] args) throws Exception {
        File file=new File("D:/算法学习测试数据集/CSV/iri.csv");
        ArrayList<AdaBoostSample> samplelist=CsvReader.csvreader(file);

        //规定模型最大训练次数
        int maxtraincounts=20;
        //进入十折
        int k = 10;//k折
        int times = 10;//times次k折
        double sum = 0;//存10x10折总平均正确率
        double sumSquares = 0;//存100个测试折准确率的平方和
        for (int t = 0; t < times; t++) {
            //第t次k折
            //统计每次十折测试集的正确率;
            double sumpercorr = 0;
            double avgpercorr = 0;
            //固定随机抽取样本的种子
            long adaboostbaseseed = 20261027L;
            KFoldEncapsulation kf = new KFoldEncapsulation(samplelist.size(), k, adaboostbaseseed + t);//这里的seed决定本次10折怎么划分样本
            for (int fold = 0; fold < k; fold++) {
                //统计每一折的正确率
                int correctnum=0;
                double percorrect=0;
                int[] train = kf.trainIndex(fold);//训练集
                int[] test = kf.testIndex(fold);//测试集

                //根据训练集下标存入对应的样本
                ArrayList<AdaBoostSample> trainsamples = new ArrayList<>();
                for (int i : train) {
                    trainsamples.add(samplelist.get(i));
                }
                //根据测试集下标存入对应样本
                ArrayList<AdaBoostSample> testsamples = new ArrayList<>();
                for (int i : test) {
                    testsamples.add(samplelist.get(i));
                }
                //用训练数据创建dataset对象
                DataSet dataSet=new DataSet(trainsamples);
                //用训练数据创建C45Tree对象
                C45DecisionTReeStump tree=new C45DecisionTReeStump(dataSet);
                //创建ovradaboost对象
                OVRAdaboostAlgorithm ovrmodel=new OVRAdaboostAlgorithm(tree,maxtraincounts);

                //用训练集对ovrmodel进行训练得到ovrsample
                OvrAdaboostSample ovrsample=ovrmodel.train(trainsamples);

                for (AdaBoostSample testsample : testsamples) {
                    int prelabel=ovrmodel.predict(testsample,ovrsample);
                    int truelabel=testsample.getLabel();
                    if(prelabel==truelabel){
                        correctnum++;
                    }
                }
                percorrect=(double)correctnum/testsamples.size();
                sumpercorr+=percorrect;
                sumSquares+=percorrect*percorrect;
            }
            avgpercorr=sumpercorr/k;
            System.out.println("第"+(t+1)+"次十折的平均准确率为:"+avgpercorr);
            sum+=avgpercorr;
        }
        double mean=sum/times;
        double variance=sumSquares/(times*k)-mean*mean;
        double standardDeviation=Math.sqrt(Math.max(variance,0.0));
        System.out.println("10x10折总平均正确率为:"+mean+"，标准差为:"+standardDeviation);
    }
}
