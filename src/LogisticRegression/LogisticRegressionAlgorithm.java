package LogisticRegression;

public class LogisticRegressionAlgorithm {
    private DataSet ds;
    private double[][] w;//第一个数组存的是第i个类别，第二个是第i个类别里每个属性的权重（因为是多分类问题，i个类别就有i个w）
    private double[] b;

    public LogisticRegressionAlgorithm(DataSet ds, double[][] w, double[] b) {
        this.ds = ds;
        this.w = w;
        this.b = b;
    }

    public double[] score(double[] x){
        double[] scorelist=new double[w.length];
        //对于一个样本，每个类别都有一套自己的w参数取值，把x分别与每个类别的w参数相乘，得分存入一个double[]里
        for (int i = 0; i < w.length; i++) {//遍历每一类的权重集合
            double score=0;//每算完一类就把得分清0
            for (int j = 0; j <w[i].length; j++) {//每一类的权重集合分别与x的取值相乘，算出得分
                score += w[i][j]*x[j];//x每个属性的取值都要与对应的类别的属性权重相乘
            }
            scorelist[i]=score+b[i];
        }
        return scorelist;
    }

    public double[] P_k(double[] x){
        //把分数数组转化为每个类别的概率数组
        double max=x[0];//找出x里面的最大值
        for (double v : x) {
            if(v>max){
                max=v;
            }
        }
        double[] X_exp=new double[x.length];
        double X_exp_sum=0;
        for (int i = 0; i < x.length; i++) {
            double x_exp=Math.exp(x[i]-max);//x数组存储的是各类的得分，这些数据最终要用exp算成概率，但Math.exp（）括号中最大为800，所以这里要做处理
            X_exp[i]=x_exp;
            X_exp_sum+=x_exp;
        }
        double[] P_k=new double[x.length];
        for (int i = 0; i < X_exp.length; i++) {
            P_k[i]=(double)X_exp[i]/X_exp_sum;
        }
        return P_k;
    }
}
