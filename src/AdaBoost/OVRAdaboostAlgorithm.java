package AdaBoost;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class OVRAdaboostAlgorithm {
    private C45DecisionTReeStump treebuilder;
    private int maxtraincounts;

    public OVRAdaboostAlgorithm(C45DecisionTReeStump treebuilder, int maxtraincounts) {
        this.treebuilder = treebuilder;
        this.maxtraincounts = maxtraincounts;
    }

    public OvrAdaboostSample train(ArrayList<AdaBoostSample> originsamplelist){
        DataSet dataSet=new DataSet(originsamplelist);
        Map<Integer,Integer> labelcount=dataSet.getLabelscount();
        //构造Ovradaboostsample,采用treemap
        Map<Integer,BinaryAdaBoostSample> ovradaboostmodel=new TreeMap<>();
        //遍历每个标签
        for (Integer label : labelcount.keySet()) {
            //每轮新建一个二分类样本列表,与该轮标签相同的样本标签设为1，不同的设为-1
            ArrayList<AdaBoostSample> newsamples=new ArrayList<>();
            int newlabel=0;
            for (AdaBoostSample originsample : originsamplelist) {
                int originlabel=originsample.getLabel();
                if(originlabel==label){
                    newlabel=1;
                }else{
                    newlabel=-1;
                }
                AdaBoostSample newsample=new AdaBoostSample(originsample.getAttribute(),newlabel);
                newsamples.add(newsample);
            }
            //每个标签单独构造一个训练方法并得到一个adaboost分类器模型
            BinaryAdaBoostAlgorithm binaryAdaBoostmodel=new BinaryAdaBoostAlgorithm(treebuilder,maxtraincounts);
            BinaryAdaBoostSample binaryAdaBoostSample=binaryAdaBoostmodel.train(newsamples);
            //将结果添加到ovradaboostsample里
            ovradaboostmodel.put(label,binaryAdaBoostSample);
        }
        OvrAdaboostSample ovradaboostsample=new OvrAdaboostSample(ovradaboostmodel);
        return ovradaboostsample;
    }

    //预测方法
    public int predict(AdaBoostSample sample,OvrAdaboostSample ovrsample){
        //得到类别->adaboost分类器的键对值
        Map<Integer,BinaryAdaBoostSample> ovrmodel=ovrsample.getOVRadaboostmodel();
        Map<Integer,Double> label_score=new TreeMap<>();
        //创建ovradaboostalgorithm的对象
        BinaryAdaBoostAlgorithm binaryAdaBoostAlgorithm=new BinaryAdaBoostAlgorithm(treebuilder,maxtraincounts);
        for (Integer label : ovrmodel.keySet()) {
            //计算每个类别的得分
            double score=0;
            BinaryAdaBoostSample binaryAdaBoostSample=ovrmodel.get(label);
            score+=binaryAdaBoostAlgorithm.predictScore(binaryAdaBoostSample,sample);
            label_score.put(label,score);
        }
        int bestlabel=-1;
        double maxscore=Double.NEGATIVE_INFINITY;
        for (Integer label : label_score.keySet()) {
            if(label_score.get(label)>maxscore){
                maxscore=label_score.get(label);
                bestlabel=label;
            }
        }
        return bestlabel;
    }

}
