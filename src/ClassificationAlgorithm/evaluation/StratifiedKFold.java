package ClassificationAlgorithm.evaluation;

import ClassificationAlgorithm.Data.DataSet;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

public class StratifiedKFold {
    private int k;//折数
    private int m;//样本总数
    ArrayList<ArrayList<Integer>> folds;//存储每一折打乱后的编号
    public StratifiedKFold(DataSet dataSet, int k, long seed){
        //先创建k个空的折
        this.folds=new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<k;i++){
            ArrayList<Integer> samplelist=new ArrayList<>();
            folds.add(samplelist);
        }
        //构造样本数
        this.m=dataSet.getSamplecount();
        //构造K折
        if (k >= 2 && k <= m) {
            this.k = k;
        } else {
            throw new IllegalArgumentException("折数不对");
        }
        //存储每一类别对应的样本编号
        TreeMap<Integer,ArrayList<Integer>> classindex=new TreeMap<>();
        for(int i=0;i<m;i++){
            int label=dataSet.getSample(i).getLabel();
            if(classindex.containsKey(label)){
                classindex.get(label).add(i);
            }else{
                ArrayList<Integer> samples=new ArrayList<>();
                samples.add(i);
                classindex.put(label,samples);
            }
        }

        Random random=new Random(seed);
        int foldcursor=0;
        //遍历每个标签内部的样本集合，对他们进行下标的洗牌
        for (Integer label : classindex.keySet()) {
            ArrayList<Integer> currentClassIndices = classindex.get(label);
            for (int i = 0; i < currentClassIndices.size(); i++) {//将刚构造的数组随机打乱
                int randomindex = i + random.nextInt(currentClassIndices.size() - i);
                Integer tempt = currentClassIndices.get(i);
                currentClassIndices.set(i, currentClassIndices.get(randomindex));
                currentClassIndices.set(randomindex, tempt);
            }
            //把打乱后的样本均匀的分散到各折
            for (Integer sampleindex : currentClassIndices) {
                folds.get(foldcursor).add(sampleindex);
                foldcursor=(foldcursor+1)%k;
            }
        }

    }
    public int getK(){
        return k;
    }//get到折数
    public int getM(){
        return m;
    }//get到样本数
    public int foldSize(int fold) {//算第fold折有多少个元素的方法
        return folds.get(fold).size();
    }
    public int[] testIndex(int fold){//创建测试数组
        ArrayList<Integer> testsamplelist=folds.get(fold);
        int[] testfold=new int[testsamplelist.size()];
        for (int i = 0; i < testfold.length; i++) {
            testfold[i]=testsamplelist.get(i);
        }
        return testfold;
    }
    public int[] trainIndex(int fold){//创建训练数组
        int[] trainfold=new int[m-foldSize(fold)];
        int writeindex=0;
        for (int i = 0; i < folds.size(); i++) {
            if(i!=fold){
                for (int j = 0; j < folds.get(i).size(); j++) {
                    trainfold[writeindex]=folds.get(i).get(j);
                    writeindex++;
                }
            }
        }
        return trainfold;
    }
}
