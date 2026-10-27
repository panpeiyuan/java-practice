package Bagging;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class C45DecisionTree {
    private DataSet dataSet;
    public C45DecisionTree(DataSet dataSet){
        this.dataSet=dataSet;
    }

    //计算样本信息熵的方法
    public double entropy(ArrayList<BaggingSample> samplelist){
        //空样本集的熵为0，
        if(samplelist.isEmpty()){
            return 0.0;
        }
        //非空样本集可以继续创建dataset对象
        DataSet dataset1=new DataSet(samplelist);
        Map<Integer,Integer> labelcount=dataset1.getLabelscount();
        double entropy=0;
        for (Integer label : labelcount.keySet()) {
            entropy+=((double)labelcount.get(label)/samplelist.size())*(Math.log((double)labelcount.get(label)/samplelist.size())/Math.log(2));
        }
        return -entropy;
    }

    //选出该组样本中出现最多的类别
    public int fallbacklabel(ArrayList<BaggingSample> samplelist) {
        DataSet dataset2 = new DataSet(samplelist);
        int fallbacklabel = -1;
        int maxlabelcount = 0;
        for (Integer label : dataset2.getLabelscount().keySet()) {
            int tempt = dataset2.getLabelscount().get(label);
            if (tempt > maxlabelcount) {
                maxlabelcount = tempt;
                fallbacklabel = label;
            }
        }
        return fallbacklabel;
    }

    public SplitResult Splitattribute(ArrayList<BaggingSample> samplelist,int attributeindex){
        //对某个特征，做特征内局部最优阈值选择，并把每个特征的最优阈值和对应的增益率存为一个对象
        //计算一个节点划分时，指定属性的所有可能的阈值，计算他们分别的信息增益率
        double[] attributevalue=new double[samplelist.size()];
        for (int i = 0; i < samplelist.size(); i++) {
            //取所有样本中某一属性的的取值
            attributevalue[i]=samplelist.get(i).getAttribute()[attributeindex];
        }
        //把属性取值从小到大排序
        Arrays.sort(attributevalue);
        //存所有候选划分点(n个取值，有n-1个划分点）
        ArrayList<Double> t=new ArrayList<>();
        for (int i = 0; i < attributevalue.length-1; i++) {
            if(attributevalue[i]==attributevalue[i+1]){
                continue;
            }
            t.add((attributevalue[i]+attributevalue[i+1])/2);
        }
        double[] gains=new double[t.size()];
        double[] GainRatios=new double[t.size()];
        //遍历所有可能的阈值，都算一遍每个阈值对应的信息增益和信息增益率并存起来
        for (int i = 0; i < t.size(); i++) {
            int D1 = 0;//属性取值小于划分点的样本个数
            ArrayList<BaggingSample> D1samplelist = new ArrayList<>();//存取值小于划分点的所有样本
            ArrayList<BaggingSample> D2samplelist = new ArrayList<>();//存取值大于划分点的所有样本
            for (BaggingSample sample : samplelist) {
                if (sample.getAttribute()[attributeindex] <= t.get(i)) {
                    D1++;
                    D1samplelist.add(sample);
                } else {
                    D2samplelist.add(sample);
                }
            }
            double tempt=((double)D1/samplelist.size())*entropy(D1samplelist)+((double)(samplelist.size()-D1)/samplelist.size())*entropy(D2samplelist);
            //计算信息增益
            double gain=entropy(samplelist)-tempt;
            gains[i]=gain;
            //计算固有值
            double instrinsicvalue=-((((double)D1samplelist.size()/samplelist.size())*((Math.log((double)D1samplelist.size()/samplelist.size()))/Math.log(2)))+(((double)D2samplelist.size()/samplelist.size())*((Math.log((double)D2samplelist.size()/samplelist.size()))/Math.log(2))));
            double Gain_Ratio=gain/instrinsicvalue;
            GainRatios[i]=Gain_Ratio;
        }
        //找最大增益率
        double max_GainRatio=-1;
        int max_GainRatioindex=-1;
        int bestthreshold=-1;
        for (int i = 0; i < GainRatios.length; i++) {
            if(GainRatios[i]>max_GainRatio){
                max_GainRatio=GainRatios[i];
                max_GainRatioindex=i;
                bestthreshold=i;
            }
        }
        if(t.size()==0){
            return null;
        }else{
            SplitResult splitResult=new SplitResult(max_GainRatio,gains[max_GainRatioindex],t.get(bestthreshold),attributeindex);
            return splitResult;
        }
    }

    public BestSplit bestSplit(ArrayList<BaggingSample> samplelist,ArrayList<Integer> availabelAttributeindex){
        //遍历所有可用特征，分别调用splitattribute，选择增益率最大的，返回对应的最佳属性和阈值
        double bestgainratio=-1;
        double bestthreshold=-1;
        int bestattributeindex=-1;
        for (Integer availabelAttribute : availabelAttributeindex) {
            SplitResult splitResult=Splitattribute(samplelist,availabelAttribute);
            if(splitResult==null){
                continue;
            }
            if(splitResult.getGainRatio()>bestgainratio){
                bestgainratio=splitResult.getGainRatio();
                bestattributeindex=splitResult.getAttributeindex();
                bestthreshold=splitResult.getThreshold();
            }
        }
        if(bestgainratio<=0){
            return null;
        }
        BestSplit bestSplit=new BestSplit(bestattributeindex,bestthreshold,bestgainratio);
        return bestSplit;
    }

    public TreeNode buildtree(ArrayList<BaggingSample> samplelist, ArrayList<Integer> avaliableattributelist){
        if(samplelist.size()==0){
            //没有该种情况的子树
            return new TreeNode(-1,samplelist);
        }
        DataSet dataset3=new DataSet(samplelist);
        if(dataset3.getLabelscount().keySet().size()==1){
            //所有样本都属于同一类别了，直接建叶节点
            return new TreeNode(samplelist.get(0).getLabel(),samplelist);
        }
        if(avaliableattributelist.size()==0){
            //剩下所有的样本，属性取值均相同，但是类别不同，就去多数类为叶节点
            return new TreeNode(fallbacklabel(samplelist),samplelist);
        }
        //调用一次bestsplit方法，算出该组样本下最佳的分类属性极其阈值
        BestSplit bestSplit=bestSplit(samplelist,avaliableattributelist);
        if(bestSplit==null){
            //bestsplit=null表示，当前节点已经没有能够产生有效信息增益的划分方式了
            return new TreeNode(fallbacklabel(samplelist),samplelist);
        }
        int bestattributeindex=bestSplit.getBestattributeindex();
        double bestthreshold=bestSplit.getBestthreshold();
        ArrayList<BaggingSample> leftsample=new ArrayList<>();
        ArrayList<BaggingSample> rightsample=new ArrayList<>();
        for (BaggingSample sample : samplelist) {
            //根据最佳属性的最佳阈值划分左右子树
            if(sample.getAttribute()[bestattributeindex]<=bestthreshold){
                leftsample.add(sample);
            }else{
                rightsample.add(sample);
            }
        }
        //构建左右子树
        TreeNode lefttree=buildtree(leftsample,avaliableattributelist);
        TreeNode righttree=buildtree(rightsample,avaliableattributelist);

        //构建当前节点
        int fallbacklabel=fallbacklabel(samplelist);//统计当前节点多数样本的下标
        TreeNode tree=new TreeNode(bestattributeindex,bestthreshold,fallbacklabel,samplelist);

        //把左右子树挂在当前节点下面
        tree.setLeftTree(lefttree);
        tree.setRightTree(righttree);

        //返回当前节点
        return tree;
    }

    //预测方法
    public int predict(TreeNode tree,BaggingSample sample){
        while(!tree.isIsleaf()){
            //先取出父节点的fallback，当出现tree.lefttree或tree.righttree为null时，直接返回父节点的fallback
            int fallback=tree.getFallback();

            int attributrindex=tree.getAttributeindex();
            double threshold=tree.getThreshold();
            if(sample.getAttribute()[attributrindex]<=threshold){
                tree=tree.getLeftTree();
            }else{
                tree=tree.getRightTree();
            }
            if(tree==null){
                return fallback;
            }
        }
        return tree.getLabel();
    }

}
