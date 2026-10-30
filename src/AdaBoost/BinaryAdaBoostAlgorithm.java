package AdaBoost;

import java.util.ArrayList;

public class BinaryAdaBoostAlgorithm {
    private final C45DecisionTReeStump treebuilder ;//存学习器类型
    private final int maxrounds;//存训练轮数

    public BinaryAdaBoostAlgorithm(C45DecisionTReeStump treebuilder, int maxrounds) {
        this.treebuilder = treebuilder;
        this.maxrounds = maxrounds;
    }

    public BinaryAdaBoostSample train(ArrayList<AdaBoostSample> samplelist){
        //创建类别对象
        ArrayList<TreeStumpNode> treenodes=new ArrayList<>();
        ArrayList<Double> treeweights=new ArrayList<>();
        //初始化样本权重
        double[] samplelistweight=new double[samplelist.size()];
        for(int i=0;i<samplelistweight.length;i++){
            samplelistweight[i]=(double)1/samplelist.size();
        }
        //训练多少个基分类器
        for(int i=0;i<maxrounds;i++) {
            TreeStumpNode treenode = treebuilder.train(samplelist, samplelistweight);
            if(treenode==null){
                break;
            }
            //分类误差率
            double errorrate = 0;
            //计算归一化因子
            double normal = 0;
            for (int j = 0; j < samplelist.size(); j++) {
                int truelabel = samplelist.get(j).getLabel();
                int prelabel = treebuilder.predict(samplelist.get(j), treenode);
                if(truelabel!=prelabel){
                    errorrate+=samplelistweight[j];
                }
            }
            if(errorrate>=0.5){
                //大于等于0.5说明该分类器与随机分类无异，没有意义
                break;
            }
            if(errorrate<1e-10){
                //错误率近似为0，使用很小的正数下限避免计算log(0)
                double safeerrorrate=1e-10;
                double treeweight=(Math.log(1-safeerrorrate)-Math.log(safeerrorrate))/2;
                treenodes.add(treenode);
                treeweights.add(treeweight);
                break;

            }
            //分类器权重
            double treeweight=(Math.log(1-errorrate)-Math.log(errorrate))/2;
            for (int j = 0; j < samplelist.size(); j++) {
                normal+=samplelistweight[j]*Math.exp(-treeweight*samplelist.get(j).getLabel()*treebuilder.predict(samplelist.get(j),treenode));
            }

            //更新训练数据的权值分布
            for (int j = 0; j < samplelistweight.length; j++) {
                samplelistweight[j]=(samplelistweight[j]/normal)*Math.exp(-treeweight*samplelist.get(j).getLabel()*treebuilder.predict(samplelist.get(j),treenode));
            }
            treenodes.add(treenode);
            treeweights.add(treeweight);
        }
        BinaryAdaBoostSample binaryAdaBoostSample =new BinaryAdaBoostSample(treenodes,treeweights);
        return binaryAdaBoostSample;
    }

    //预测方法，根据所有模型及其权重，算出最终得分
    public double predictScore(BinaryAdaBoostSample binaryAdaBoostSample, AdaBoostSample sample){
        //提取adaboost对象中存储的所有基学习器及其系数
        ArrayList<TreeStumpNode> treemodels= binaryAdaBoostSample.getTreelist();
        ArrayList<Double> treesweight= binaryAdaBoostSample.getTreeWeight();
        //计算最终得分
        double score=0;
        for (int i = 0; i < treemodels.size(); i++) {
            score+=(treesweight.get(i))*(treebuilder.predict(sample,treemodels.get(i)));
        }
        return score;
    }

    //对最终得分调用sign函数，得分>=0返回1，得分<0返回-1
    public int predict(BinaryAdaBoostSample binaryAdaBoostSample, AdaBoostSample sample){
        double score=predictScore(binaryAdaBoostSample,sample);
        if(score>=0){
            return 1;
        }else{
            return -1;
        }
    }

}
