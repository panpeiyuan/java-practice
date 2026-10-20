package LogisticRegression;

import chap11.KFoldEncapsulation;

import java.io.File;
import java.util.ArrayList;

public class TenbyTen {
    public static void main(String[] args) throws Exception {
        File file = new File("D:/算法学习测试数据集/CSV/win.csv");
        //原样本列表
        ArrayList<LogisticSample> logisticSamples = CsvReader.csvreader(file);

        int k = 10;//k折
        int times = 10;//times次k折
        for (int t = 0; t < times; t++) {//第t次k折
            KFoldEncapsulation kf = new KFoldEncapsulation(logisticSamples.size(), k);//m个样本，做k折
            for (int fold = 0; fold < k; fold++) {
                int[] train = kf.trainIndex(fold);//训练集
                int[] test = kf.testIndex(fold);//测试集

                //根据训练集下标存入对应的样本
                ArrayList<LogisticSample> trainsample = new ArrayList<>();
                for (int i : train) {
                    trainsample.add(logisticSamples.get(i));
                }
                //根据测试集下标存入对应样本
                ArrayList<LogisticSample> testsample = new ArrayList<>();
                for (int i : test) {
                    testsample.add(logisticSamples.get(i));
                }

                //新建normallization对象，对train进行操作
                Normalization normal=new Normalization();
                normal.fit(trainsample);//算出训练集的均值方差

                //用训练集的均值和方差对训练集和测试集进行标准化
                ArrayList<LogisticSample> normaltrain=normal.apply(trainsample);
                ArrayList<LogisticSample> normaltest=normal.apply(testsample);

                DataSet ds=new DataSet(normaltrain);
                LogisticRegressionAlgorithm logisticmodel=new  LogisticRegressionAlgorithm(ds);
                logisticmodel.parametertrain(20000,2);

                for (LogisticSample testSample : normaltest) {

                }
            }
        }
    }
}
