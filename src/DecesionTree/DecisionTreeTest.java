package DecesionTree;

import chap11.KFoldEncapsulation;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DecisionTreeTest {
    public static void main(String[] args) throws Exception {
        File file = new File("D:/算法学习测试数据集/CSV/hay.csv");
        ArrayList<Sample> sample = CsvReader.csvreader(file);
        System.out.println("样本数有" + sample.size() + "个");
        int attnumber = sample.get(1).getAttributevalue().length;//从s中选取一个对象，调用第一个对象的属性值数组，然后统计属性值数组的长度
        System.out.println("属性值有" + attnumber + "个");
        DataSet ds=new DataSet(sample);//创数据清理对象
        DecisionTree dt=new DecisionTree(ds);//创决策树对象
        ArrayList<Integer> sampleindex=new ArrayList<>();//造一个数组把dataset里的数据下标移过来（方便作为后续方法的输入）
        double[] alphas={0,0.1,0.25,0.5,1,2,3,4,5,8,12,20};//创建一个损失函数中参数a的取值列表
        for(int i=0;i<ds.size();i++){
            sampleindex.add(i);
        }
        ArrayList<Integer> attindex=new ArrayList<>();//造一个数组，存属性下标(方便作为后续方法的输入）
        for(int i=0;i<ds.getAttnumber();i++){
            attindex.add(i);
        }
        Set<TreeNode> treesample=new HashSet<>();//存剪枝后所有的决策树对象
        // 1. 建树 + 打印
        TreeNode root = dt.buildtree(sampleindex, attindex);//以所有样本进行建树
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
        double[] accs = new double[times * k];//存所有测试集的正确率

        int p = 0;
        ArrayList<Double> traincorrect=new ArrayList<>();
        for (int t = 0; t < times; t++) {//第t次k折
            KFoldEncapsulation kf = new KFoldEncapsulation(m, k);//m个样本，做k折
            for (int fold = 0; fold < k; fold++) {
                int[] train = kf.trainIndex(fold);
                int cut=train.length/3;
                ArrayList<Integer> cutlist=new ArrayList<>();
                for (int i = 0; i < cut; i++) {//取train数组中前1/3为训练集的验证集
                    cutlist.add(train[i]);
                }
                int[] finaltrain=new int[train.length-cutlist.size()];//最终的训练集（除去验证集）
                for (int i = 0; i < finaltrain.length; i++) {//把剩下的训练集添加到finaltrain数组中
                    finaltrain[i]=train[cutlist.size()+i];
                }
                int[] test = kf.testIndex(fold);

                ArrayList<Integer> trainList = new ArrayList<>();
                for (int i : finaltrain) {//把训练集的样本下标存入一个arraylist集合(因为后面的方法接受的都是集合）
                    trainList.add(i);
                }

                TreeNode tree = dt.buildtree(trainList, attindex);// 每折以训练集作为输入重新建树

                for(int i=0;i<alphas.length;i++){//剪枝
                    TreeNode cuttree=dt.postpruning(tree,alphas[i]);
                    treesample.add(cuttree);
                    int traincorr=0;//统计验证集的正确数目
                    for (int i1 = 0; i1 < cutlist.size(); i1++) {
                        int cutindex=cutlist.get(i1);
                        if(dt.predict(cuttree,ds.get(cutindex).getAttributevalue())==ds.get(cutindex).getLabel()){
                            traincorr++;
                        }
                    }
                    traincorrect.add((double)traincorr/cutlist.size());
                }

                int correcttest = 0;//存答对的样本数
                for (int s : test) {//把测试集放入predict方法，进行判断
                    if (dt.predict(tree, ds.get(s).getAttributevalue()) == ds.get(s).getLabel()) {
                        correcttest++;
                    }
                }
                accs[p] = (double) correcttest / test.length;//存每折的正确率
                p++;
            }
        }
        double sum=0;
        for (Double v : traincorrect) {
            sum+=v;
        }
        System.out.println("10次10折交叉验证中验证集的正确率均值为"+sum/traincorrect.size());
        System.out.println("一共进行了"+p+"次实验");// 应该是 100


        double lostfunction= dt.losingfunction(root);
        System.out.println("该决策树损失函数的值为:"+lostfunction);

        // 4. 均值 + 标准差
        double sum1 = 0;
        for (double v : accs) sum1 += v;//把所有答对数求和
        double mean = sum1 / accs.length;//求正确率均值
        double sum2 = 0;
        for (double v : accs) sum2 += (v - mean) * (v - mean);
        System.out.println("10×10次交叉验证所有测试集的正确率均值为 " + mean + "，标准差为" + Math.sqrt(sum2 / accs.length));
    }
}
