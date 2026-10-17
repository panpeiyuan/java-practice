package Bayes;


import chap11.KFoldEncapsulation;

import java.io.File;
import java.util.ArrayList;

public class TenByTen {
    public static void main(String[] args) throws Exception {
        File file = new File("D:/算法学习测试数据集/CSV/hay.csv");
        ArrayList<Bayessample> bayessamples = CsvReader.csvreader(file);
        DataSet ds = new DataSet(bayessamples);//创建一个dataset对象
        BayesAlgorithm bayes = new BayesAlgorithm(ds);//创建一个bayesalgorithm对象

        int m = ds.size(), k = 10, times = 10;
        ArrayList<Double> acctest =new ArrayList<>();//存所有测试集的正确率

        int count=1;

        for (int t = 0; t < times; t++) {//第t次k折
            KFoldEncapsulation kf = new KFoldEncapsulation(m, k);//m个样本，做k折
            for (int fold = 0; fold < k; fold++) {
                int[] train = kf.trainIndex(fold);//训练集
                int[] test = kf.testIndex(fold);//测试集

                //根据训练集下标存入对应的样本
                ArrayList<Bayessample> trainsample=new ArrayList<>();
                for (int i : train) {
                    trainsample.add(ds.get(i));
                }
                //根据测试集下标存入对应样本
                ArrayList<Bayessample> testsample=new ArrayList<>();
                for (int i : test) {
                    testsample.add(ds.get(i));
                }

                //根据train的对象创建trainds，目的是用trainds再创建BayesAlgorithm的对象，从而调用train方法，计算prior和candprobs的取值表
                DataSet trainds=new DataSet(trainsample);
                BayesAlgorithm model=new BayesAlgorithm(trainds);
                model.train();//创建出来了prior和candprob取值表

                int precorrect=0;
                for (Bayessample bayessample : testsample) {
                    System.out.print("第"+count+"个测试样本的预测标签为:"+model.predict(bayessample)+", ");
                    System.out.println("实际标签为:"+bayessample.getLabel());
                    count++;
                    if(bayessample.getLabel()==model.predict(bayessample)){
                        precorrect++;
                    }
                }
                double precorrectpercent=(double)precorrect/testsample.size();
                acctest.add(precorrectpercent);

            }
        }

        //算10x10折平均正确率
        double  sum=0;
        for (Double v : acctest) {
            sum+=v;
        }
        double avg_acc=sum/acctest.size();
        System.out.println("10x10折交叉验证，测试集平均正确率为:"+avg_acc);

        //算10x10折正确率的标准差
        double sum2=0;
        for (Double v : acctest) {
            sum2+=(v-avg_acc)*(v-avg_acc);
        }
        double std=Math.sqrt(sum2/acctest.size());
        System.out.println("10x10折交叉验证，测试集正确率的标准差为:"+std);
    }
}
