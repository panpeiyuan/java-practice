package LogisticRegression;

import KFold.KFoldEncapsulation;

import java.io.File;
import java.util.ArrayList;

public class TenbyTen {
    public static void main(String[] args) throws Exception {
        File file = new File("D:/算法学习测试数据集/CSV/win.csv");
        //原样本列表
        ArrayList<LogisticSample> logisticSamples = CsvReader.csvreader(file);

        int k = 10;//k折
        int times = 10;//times次k折
        double sum=0;//存10x10折总平均正确率
        for (int t = 0; t < times; t++) {//第t次k折
            //统计每折测试集的正确率
            double percorrect=0;

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

                int correctnum=0;
                for (LogisticSample testSample : normaltest) {
                    //每一折统计一次测试集合正确个数
                    if(logisticmodel.predict(testSample)==testSample.getLabel()){
                        correctnum++;
                    }
                }
                percorrect+=(double)correctnum/normaltest.size();
            }
            sum+=percorrect;
            System.out.println("第"+(t+1)+"轮十折，验证集平均的准确率为:"+percorrect/10);
        }
        System.out.println("10x10折交叉验证,总平均正确率为:"+sum/100);
    }
}
