package TwoClassNeuralNetwork;

import KFold.KFoldEncapsulation;

import java.io.File;
import java.util.ArrayList;

public class TenbyTen {
    public static void main(String[] args) throws Exception {
        File file = new File("D:/算法学习测试数据集/CSV/iri.csv");
        //原样本列表
        ArrayList<NeuralnetSample> originsamplelist = CsvReader.csvreader(file);

        int k = 10;//k折
        int times = 10;//times次k折
        double sum=0;//存10x10折总平均正确率
        for (int t = 0; t < times; t++) {//第t次k折
            //统计每次十折测试集的正确率;
            double sumpercorr=0;
            long seed=20261023L+t;
            KFoldEncapsulation kf = new KFoldEncapsulation(originsamplelist.size(), k,seed);//m个样本，做k折
            for (int fold = 0; fold < k; fold++) {
                int[] train = kf.trainIndex(fold);//训练集
                int[] test = kf.testIndex(fold);//测试集

                //根据训练集下标存入对应的样本
                ArrayList<NeuralnetSample> trainsample = new ArrayList<>();
                for (int i : train) {
                    trainsample.add(originsamplelist.get(i));
                }
                //根据测试集下标存入对应样本
                ArrayList<NeuralnetSample> testsample = new ArrayList<>();
                for (int i : test) {
                    testsample.add(originsamplelist.get(i));
                }

                //新建normallization对象，对train进行操作
                Normalization normal = new Normalization();
                normal.fit(trainsample);//算出训练集的均值方差

                //用训练集的均值和方差对训练集和测试集进行标准化
                ArrayList<NeuralnetSample> normaltrain = normal.apply(trainsample);
                ArrayList<NeuralnetSample> normaltest = normal.apply(testsample);

                DataSet dataSet = new DataSet(normaltrain);
                ForwardPropagation unit=new ForwardPropagation(dataSet.getAttnumber(), 3,1,333);
                double[] loselist=unit.train(dataSet,5000,0.1);
                int corrnum=0;
                for (NeuralnetSample Sample : normaltest) {
                    int prelabel=unit.predict(Sample);
                    if(prelabel==Sample.getLabel()){
                        corrnum++;
                    }
                }
                sumpercorr+=(double)corrnum/testsample.size();
            }
            System.out.println("第"+(t+1)+"次十折,平均正确率为"+sumpercorr/10);
        }
    }
}
