package DecesionTree;

import chap11.KFoldEncapsulation;

import java.io.File;
import java.util.ArrayList;

public class DecisionTreeTest {
    public static void main(String[] args) throws Exception {
        File file = new File("D:/算法学习测试数据集/CSV/hay.csv");
        ArrayList<Sample> sample = CsvReader.csvreader(file);
        System.out.println("样本数有" + sample.size() + "个");
        int attnumber = sample.get(1).getAttributevalue().length;//从s中选取一个对象，调用第一个对象的属性值数组，然后统计属性值数组的长度
        System.out.println("属性值有" + attnumber + "个");
        DataSet ds=new DataSet(sample);//创数据清理对象
        DecisionTree dt=new DecisionTree(ds);//创决策树对象
        ArrayList<Integer> sampleindex=new ArrayList<>();//造一个数组把dataset里的数据下标移过来
        for(int i=0;i<ds.size();i++){
            sampleindex.add(i);
        }
        ArrayList<Integer> attindex=new ArrayList<>();//造一个数组，存属性下标
        for(int i=0;i<ds.getAttnumber();i++){
            attindex.add(i);
        }
        // 1. 建树 + 打印
        TreeNode root = dt.buildtree(sampleindex, attindex);
        dt.printtree(root, 0);

        // 2. 训练集自测
        int wrong = 0;
        for (int i = 0; i < ds.size(); i++) {
            if (dt.predict(root, ds.get(i).getAttributevalue()) != ds.get(i).getLabel()) {
                wrong++;
            }
        }
        System.out.println("训练集自测：错 " + wrong + " / " + ds.size());

        // 3. 10 次 10 折
        int m = ds.size(), k = 10, times = 10;
        double[] accs = new double[times * k];
        int p = 0;
        for (int t = 0; t < times; t++) {
            KFoldEncapsulation kf = new KFoldEncapsulation(m, k);
            for (int fold = 0; fold < k; fold++) {
                int[] train = kf.trainIndex(fold);
                int[] test = kf.testIndex(fold);

                ArrayList<Integer> trainList = new ArrayList<>();
                for (int i : train) {
                    trainList.add(i);
                }

                TreeNode tree = dt.buildtree(trainList, attindex);   // 每折重新建树

                int correct = 0;
                for (int s : test) {
                    if (dt.predict(tree, ds.get(s).getAttributevalue()) == ds.get(s).getLabel()) {
                        correct++;
                    }
                }
                accs[p] = (double) correct / test.length;
                p++;
            }
        }
        System.out.println("收集到的准确率个数：" + p);        // 应该是 100

        // 4. 均值 + 标准差
        double sum = 0;
        for (double v : accs) sum += v;
        double mean = sum / accs.length;
        double sum2 = 0;
        for (double v : accs) sum2 += (v - mean) * (v - mean);
        System.out.println("10×10 均值 " + mean + "，标准差 " + Math.sqrt(sum2 / accs.length));


    }
}
