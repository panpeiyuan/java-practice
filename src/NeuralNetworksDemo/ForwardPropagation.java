package NeuralNetworksDemo;

import java.util.Random;

public class ForwardPropagation {
    private int inputUnitCount;
    private int hidenUnitCount;
    private int outputUnitCount;
    private double[][] w1;//存输入层到隐藏层的参数
    private double[] b1;//存输入层到隐藏层的偏置

    private double[][] w2;//存隐藏层到输出层的参数
    private double[] b2;//存隐藏层得到输出层的偏置

    public ForwardPropagation(int inputUnitCount, int hidenUnitCount, int outputUnitCount,long seed) {
        this.inputUnitCount = inputUnitCount;
        this.hidenUnitCount = hidenUnitCount;
        this.outputUnitCount = outputUnitCount;

        w1=new double[hidenUnitCount][inputUnitCount];
        b1=new double[hidenUnitCount];

        w2=new double[outputUnitCount][hidenUnitCount];
        b2=new double[outputUnitCount];

        //保证每次随机初始的参数都一致
        initializeParameters(seed);
    }

    public void initializeParameters(long seed){
        //初始化各参数
        Random random=new Random(seed);
        for (int i = 0; i < w1.length; i++) {
            for(int j=0;j<w1[i].length;j++){
                w1[i][j]=random.nextDouble()-0.5;
            }
            b1[i]=0.0;
        }
        for (int i = 0; i < w2.length; i++) {
            for(int j=0;j<w2[i].length;j++){
                w2[i][j]=random.nextDouble()-0.5;
            }
            b2[i]=0.0;
        }
    }

    //前向传播得到各个中间值，并根据中间值创建forwardresult对象
    public ForwardResult forward(double[] input){
        double[] z1=new double[hidenUnitCount];
        double[] hiddenoutput=new double[z1.length];
        double[] z2=new double[outputUnitCount];
        double[] output=new double[z2.length];
        for (int i = 0; i < w1.length; i++) {
            double value=b1[i];
            for (int j = 0; j < w1[i].length; j++) {
                value+=w1[i][j]*input[j];
            }
            z1[i]=value;
        }
        for (int i = 0; i < z1.length; i++) {
            double hiddenvalue=(double)1/(1+Math.exp(-z1[i]));
            hiddenoutput[i]=hiddenvalue;
        }
        for (int i = 0; i < w2.length; i++) {
            double value=b2[i];
            for (int j = 0; j < hiddenoutput.length; j++) {
                value+=w2[i][j]*hiddenoutput[j];
            }
            z2[i]=value;
        }
        for (int i = 0; i < w2.length; i++) {
            double outputvalue=(double)1/(1+Math.exp(-z2[i]));
            output[i]=outputvalue;
        }
        ForwardResult fr=new ForwardResult(input,z1,hiddenoutput,z2,output);
        return fr;
    }

    public Gradients backward(ForwardResult forwardResult,int label){
        //使用平方误差作为损失函数
        double y_hat=forwardResult.getOutput()[0];//二分类问题只有一个输出概率
        int y=label;
        double losefunction=(double)(y_hat-y)*(y_hat-y)/2;
        //构造一个对象用来存储后向传播得到的梯度
        Gradients grad=new Gradients(inputUnitCount,hidenUnitCount,outputUnitCount);

        //计算w2的梯度并存入Gradients中
        double[][] gradw2=new double[outputUnitCount][hidenUnitCount];
        for(int i=0;i<outputUnitCount;i++){
            for(int j=0;j<hidenUnitCount;j++){
                double gradvalue=(y_hat-y)*y_hat*(1-y_hat)*forwardResult.getHiddenoutput()[j];
                gradw2[i][j]=gradvalue;
            }
        }
        grad.setGradW2(gradw2);

        //计算b2的梯度并存入Gradients中
        double[] gradb2=new double[outputUnitCount];
        double bvalue=(y_hat-y)*y_hat*(1-y_hat);
        for (int i = 0; i < gradb2.length; i++) {
            gradb2[i]=bvalue;
        }
        grad.setGradB2(gradb2);
}
