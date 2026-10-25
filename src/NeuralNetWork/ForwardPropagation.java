package NeuralNetWork;

import java.util.ArrayList;
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

    public Gradients backward(ForwardResult forwardResult,int label) {
        //得到预测概率数组
        double[] y_hat = forwardResult.getOutput();
        int y = label;
        //创建一个gradients对象存储backword的结果
        Gradients gradients=new Gradients(inputUnitCount,hidenUnitCount,outputUnitCount);

        //计算w2梯度并存入Gradients中
        double[][] gradw2=new double[outputUnitCount][hidenUnitCount];
        for (int i = 0; i < gradw2.length; i++) {
            for (int j = 0; j < gradw2[i].length; j++) {
                if(i==y){
                    gradw2[i][j]=(forwardResult.getOutput()[i]-1)*y_hat[i]*(1-y_hat[i])*forwardResult.getHiddenoutput()[j];
                }else{
                    gradw2[i][j]=(forwardResult.getOutput()[i]-0)*y_hat[i]*(1-y_hat[i])*forwardResult.getHiddenoutput()[j];
                }
            }
        }
        gradients.setGradW2(gradw2);

        //计算b2的梯度并存入Gradients中
        double[] gradb2 = new double[outputUnitCount];
        for (int i = 0; i < gradb2.length; i++) {
            if(i==y){
                gradb2[i]=(y_hat[i]-1)*y_hat[i]*(1-y_hat[i]);
            }else{
                gradb2[i]=(y_hat[i]-0)*y_hat[i]*(1-y_hat[i]);
            }
        }
        gradients.setGradB2(gradb2);

        //计算w1和b1的梯度并存入Gradients中
        double[][] gradw1 = new double[hidenUnitCount][inputUnitCount];
        double[] gradb1 = new double[hidenUnitCount];

        for (int i = 0; i < hidenUnitCount; i++) {
            double sum=0;
            for (int j = 0; j < outputUnitCount; j++) {
                sum+=w2[j][i]*gradb2[j];
            }
            gradb1[i]=forwardResult.getHiddenoutput()[i]*(1-forwardResult.getHiddenoutput()[i])*sum;
            for(int j=0;j<inputUnitCount;j++){
                gradw1[i][j]=gradb1[i]*forwardResult.getX()[j];
            }
        }
        gradients.setGradB1(gradb1);
        gradients.setGradW1(gradw1);
        return gradients;
    }

    public void UpdataParameters(Gradients gradients,double learnrate){
        //更新参数的方法
        for (int i = 0; i < w1.length; i++) {
            for (int j = 0; j < w1[i].length; j++) {
                w1[i][j]-=gradients.getGradW1()[i][j]*learnrate;
            }
        }
        for (int i = 0; i < w2.length; i++) {
            for (int j = 0; j < w2[i].length; j++) {
                w2[i][j]-=gradients.getGradW2()[i][j]*learnrate;
            }
        }
        for (int i = 0; i < b1.length; i++) {
            b1[i]-=gradients.getGradB1()[i]*learnrate;
        }
        for (int i = 0; i < b2.length; i++) {
            b2[i]-=gradients.getGradB2()[i]*learnrate;
        }
    }

    public double[] train(DataSet dataSet,int times,double learnrate){
        //根据训练样本训练参数的方法，返回每轮训练后的平均损失
        //存每轮的平均损失
        double[] avg_loss=new double[times];
        for(int i=0;i<times;i++){
            Gradients gradients=new Gradients(inputUnitCount,hidenUnitCount,outputUnitCount);
            double sumlosefunction=0;
            ArrayList<NeuralnetSample> samplelist=dataSet.getSamplelist();
            for (NeuralnetSample neuralnetSample : samplelist) {
                //多分类问题
                ForwardResult fr=forward(neuralnetSample.getAttributevalue());
                //得到多分类问题每个类别的概率矩阵
                double[] prelabel=fr.getOutput();
                int truelabel=neuralnetSample.getLabel();
                //计算该样的损失并加入总损失中
                for (int i1 = 0; i1 < prelabel.length; i1++) {
                    if(i1==truelabel){
                        sumlosefunction+=((prelabel[i1]-1)*(prelabel[i1]-1))/2;
                    }else{
                        sumlosefunction+=(prelabel[i1]*prelabel[i1]/2);
                    }
                }
                //得到这个样本的四组梯度
                Gradients gradient=backward(fr,truelabel);
                //把这个样本的四组梯度依次加到一个总梯度对象里
                for (int i1 = 0; i1 < gradient.getGradW1().length; i1++) {
                    for (int j = 0; j < gradient.getGradW1()[i1].length; j++) {
                        gradients.getGradW1()[i1][j]+=gradient.getGradW1()[i1][j];
                    }
                }

                for (int i1 = 0; i1 < gradient.getGradW2().length; i1++) {
                    for (int j = 0; j < gradient.getGradW2()[i1].length; j++) {
                        gradients.getGradW2()[i1][j]+=gradient.getGradW2()[i1][j];
                    }
                }

                for (int i1 = 0; i1 < gradient.getGradB1().length; i1++) {
                    gradients.getGradB1()[i1]+=gradient.getGradB1()[i1];
                }

                for (int i1 = 0; i1 < gradient.getGradB2().length; i1++) {
                    gradients.getGradB2()[i1]+=gradient.getGradB2()[i1];
                }
            }
            //四组总梯度除以样本数，取均值
            int samplenum=samplelist.size();
            for (int i1 = 0; i1 < gradients.getGradW1().length; i1++) {
                for (int j = 0; j < gradients.getGradW1()[i1].length; j++) {
                    gradients.getGradW1()[i1][j]=gradients.getGradW1()[i1][j]/samplenum;
                }
            }

            for (int i1 = 0; i1 < gradients.getGradW2().length; i1++) {
                for (int j = 0; j < gradients.getGradW2()[i1].length; j++) {
                    gradients.getGradW2()[i1][j]=gradients.getGradW2()[i1][j]/samplenum;
                }
            }

            for (int i1 = 0; i1 < gradients.getGradB1().length; i1++) {
                gradients.getGradB1()[i1]=gradients.getGradB1()[i1]/samplenum;
            }

            for (int i1 = 0; i1 < gradients.getGradB2().length; i1++) {
                gradients.getGradB2()[i1]=gradients.getGradB2()[i1]/samplenum;
            }
            //更新参数，在下一轮时使用新参数
            UpdataParameters(gradients,learnrate);
            //把该轮的平均损失存入数组
            avg_loss[i]=sumlosefunction/samplelist.size();
        }
        return avg_loss;
    }

    public int predict(NeuralnetSample sample){
        int prelabel=0;
        double maxgrade=-1;
        int bestindex=-1;
        ForwardResult result=forward(sample.getAttributevalue());
        for (int i = 0; i < result.getOutput().length; i++) {
            if(result.getOutput()[i]>maxgrade){
                maxgrade=result.getOutput()[i];
                bestindex=i;
            }
        }
        prelabel=bestindex;
        return prelabel;
    }
}
