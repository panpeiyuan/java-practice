package LogisticRegression;

import java.util.*;

public class LogisticRegressionAlgorithm {
    private DataSet ds;
    // 用标签作为 key
    private TreeMap<Integer, double[]> w; // 存每个标签对应的权重向量数组
    private TreeMap<Integer, Double> b;   // 存每个标签对应的常数量

    public LogisticRegressionAlgorithm(DataSet ds) {
        this.ds = ds;
        //用Treemap实现map，能自动实现升序排列
        this.w = new TreeMap<>();
        this.b = new TreeMap<>();

        int d = ds.getAttnumber();
        for (int label : ds.getLabelscount().keySet()) {
            //先通过ds，把w和b的key按照类别的升序构建
            w.put(label, new double[d]);
            b.put(label, 0.0);
        }
    }

    public Map<Integer, Double> score(double[] x) {
        //给定一个样本的属性取值，返回他可能取到的每个类别的得分，以类别为key，类别得分为value
        Map<Integer, Double> scores = new TreeMap<>();

        for (int label : w.keySet()) {
            //遍历所有类别
            double[] weights = w.get(label);//初始化的权重数组
            double score = b.get(label);//先在得分中把b存进去

            for (int j = 0; j < weights.length; j++) {
                score += weights[j] * x[j];//样本第i个位置属性的取值，乘以第i个属性的权重
            }
            scores.put(label, score);
        }
        return scores;
    }

    public Map<Integer, Double> P_k(Map<Integer, Double> scores) {
        // 把每个列别的得分计算为概率，类别为key，概率为value
        double max = Collections.max(scores.values());

        Map<Integer, Double> probs = new TreeMap<>();
        double sum = 0.0;

        for (Map.Entry<Integer, Double> entry : scores.entrySet()) {
            double exp = Math.exp(entry.getValue() - max);
            probs.put(entry.getKey(), exp);
            sum += exp;
        }

        for (Map.Entry<Integer, Double> entry : probs.entrySet()) {
            probs.put(entry.getKey(), entry.getValue() / sum);
        }
        return probs;
    }

    public double LikehoodFunction(ArrayList<LogisticSample> samplelist) {
        //多项逻辑斯蒂回归的似然函数
        //二分类的逻辑斯蒂回归用sigmoid，多分类的逻辑斯蒂回归用softmax（本处就是用的softmax）
        double lfvalue = 0;
        for (LogisticSample logisticSample : samplelist) {
            Map<Integer, Double> probs = P_k(score(logisticSample.getAttributevalue()));
            double temptp=probs.get(logisticSample.getLabel());
            if(temptp< 1e-15){//防止下溢
                lfvalue+=Math.log(1e-15);
            }else{
                lfvalue += Math.log(probs.get(logisticSample.getLabel()));
            }
        }
        return lfvalue;
    }

    public void parametertrain(int times, double learningrate) {
        //进行参数训练的方法
        for (int i = 0; i < times; i++) {
            //创建两个根据label取每个属性偏导的map，每次循环都刷新
            TreeMap<Integer, double[]> gradw = new TreeMap<>();
            TreeMap<Integer, Double> gradb = new TreeMap<>();

            //先给每个类别准备一个全0的梯度(逻辑斯蒂回归参数从0开始训练)
            int d = ds.getAttnumber();//求出样本属性个数
            for (Integer label : w.keySet()) {
                //给gradw和gradb加上标签
                gradw.put(label, new double[d]);
                gradb.put(label, 0.0);
            }
            for (LogisticSample logisticSample : ds.getSamplelist()) {//遍历样本集每一个样本
                //labelpre存每个类别的预测概率
                Map<Integer, Double> labelpre = P_k(score(logisticSample.getAttributevalue()));
                //该样本的真实标签
                int truelabel = logisticSample.getLabel();
                //遍历所有的预测标签
                Set<Integer> preprob = labelpre.keySet();

                //对于每一个类别都
                for (Integer integer : preprob) {
                    double error = 0;//error表示对于这个样本，预测的类别错的有多严重（三个预测类别分别计算）
                    if (truelabel == integer) {//预测正确，error表示模型不自信的程度，此时error>0,此类得分需要上升
                        error = 1 - labelpre.get(integer);
                    } else {//预测错误，error表示模型给错了太多概率，此时error<0，此类得分需要下降
                        error = 0 - labelpre.get(integer);
                    }
                    //得到该类别下的属性权重
                    double[] gw=gradw.get(integer);
                    for(int j=0;j<ds.getAttnumber();j++){
                        gw[j]+=error*logisticSample.getAttributevalue()[j];//误差乘样本在各个属性下的取值
                    }
                    gradb.put(integer, gradb.get(integer) + error);//对bk的偏导，常数b在各个预测类别的取值+error
                }
            }

            for (Integer label : w.keySet()) {
                //把gw里的权重迭代数据更新到w和b里
                double[] weights=w.get(label);
                double[] gw=gradw.get(label);

                for(int j=0;j<ds.getAttnumber();j++){
                    weights[j]+=learningrate*gw[j]/ds.size();//更新权重
                }

                b.put(label,b.get(label)+learningrate*gradb.get(label)/ds.size());
            }
        }
    }

    public int predict(LogisticSample sample){
        Map<Integer,Double> premap=P_k(score(sample.getAttributevalue()));
        double maxp=-1;
        int bestlabel=Integer.MIN_VALUE;
        for (Integer prelabel : premap.keySet()) {
            if(premap.get(prelabel)>maxp){
                maxp=premap.get(prelabel);
                bestlabel=prelabel;
            }
        }
        return bestlabel;
    }
}
