package Kmeans;

import KFold.KFoldEncapsulation;

import java.io.File;
import java.util.ArrayList;

public class TenByTen {
    public static void main(String[] args) throws Exception {
        File file = new File("D:/算法学习测试数据集/CSV/win.csv");
        //原样本列表
        ArrayList<KmeansSample> samplelist = CsvReader.csvreader(file);

        int kfold = 10;//k折
        int kmeans=3;//kmeans簇数
        int maxiterations=300;//最大训练轮数
        int times = 10;//times次k折
        long seed=20261103L;
        double[] testMses = new double[times * kfold];
        int resultIndex = 0;

        for (int t = 0; t < times; t++) {//第t次k折
            KFoldEncapsulation kf =
                    new KFoldEncapsulation(samplelist.size(), kfold, seed + t);
            for (int fold = 0; fold < kfold; fold++) {
                int[] train = kf.trainIndex(fold);//训练集
                int[] test = kf.testIndex(fold);//测试集

                //根据训练集下标存入对应的样本
                ArrayList<KmeansSample> trainsample = new ArrayList<>();
                for (int i : train) {
                    trainsample.add(samplelist.get(i));
                }
                //根据测试集下标存入对应样本
                ArrayList<KmeansSample> testsample = new ArrayList<>();
                for (int i : test) {
                    testsample.add(samplelist.get(i));
                }

                //新建normallization对象，对train进行操作
                Kmeans.Normalization normal = new Normalization();
                normal.fit(trainsample);//算出训练集的均值方差

                //用训练集的均值和方差对训练集和测试集进行标准化
                ArrayList<KmeansSample> normaltrain = normal.apply(trainsample);
                ArrayList<KmeansSample> normaltest = normal.apply(testsample);

                DataSet dataSet = new DataSet(normaltrain);
                KmeansAlgorithm kmeansmodel=new KmeansAlgorithm(dataSet,kmeans,maxiterations,seed);
                //在训练折上运行kmeans
                kmeansmodel.fit(normaltrain,kmeans);
                //把每个测试样本分配到最近的训练中心
                int[] testcluster=new int[testsample.size()];
                for (int i = 0; i < normaltest.size(); i++) {
                    double mindistance=Double.POSITIVE_INFINITY;
                    int mincenterindex=-1;
                    double[] testsamplevalue=normaltest.get(i).getAttribute();
                    for (int j = 0; j < kmeansmodel.centers.length; j++) {
                        double[] clustervalue=kmeansmodel.centers[j];
                        double distance=Distance.squaredeuclidean(testsamplevalue,clustervalue);
                        if(distance<mindistance){
                            mindistance=distance;
                            mincenterindex=j;
                        }
                    }
                    testcluster[i]=mincenterindex;
                }
                //计算测试集的mse
                double testSquaredDistanceSum=0;
                for (int i = 0; i < testcluster.length; i++) {
                    int cluster=testcluster[i];
                    double[] testvalue=normaltest.get(i).getAttribute();
                    double[] centervalue=kmeansmodel.centers[cluster];
                    testSquaredDistanceSum +=
                            Distance.squaredeuclidean(testvalue, centervalue);
                }
                double testMse = testSquaredDistanceSum / normaltest.size();
                testMses[resultIndex] = testMse;
                resultIndex++;
                System.out.println(
                        "第" + (t + 1) + "次10折第" + (fold + 1)
                                + "折的样本平均平方距离为" + testMse);
            }
        }

        double sum = 0;
        double minimum = Double.POSITIVE_INFINITY;
        double maximum = Double.NEGATIVE_INFINITY;
        for (double testMse : testMses) {
            sum += testMse;
            minimum = Math.min(minimum, testMse);
            maximum = Math.max(maximum, testMse);
        }

        double mean = sum / testMses.length;
        double squaredDifferenceSum = 0;
        for (double testMse : testMses) {
            double difference = testMse - mean;
            squaredDifferenceSum += difference * difference;
        }
        double standardDeviation =
                Math.sqrt(squaredDifferenceSum / testMses.length);

        System.out.println("10×10运行次数:" + resultIndex);
        System.out.println("测试MSE均值:" + mean);
        System.out.println("测试MSE标准差:" + standardDeviation);
        System.out.println("测试MSE最小值:" + minimum);
        System.out.println("测试MSE最大值:" + maximum);
    }
}
