package ClassificationAlgorithm.Algorithm;

import ClassificationAlgorithm.Data.DataSet;
import ClassificationAlgorithm.Data.Sample;
import ClassificationAlgorithm.PreProcessing.Standardize;
import Knn.KnnAlgorithm;

public class KnnClassifier implements Classifier{
    private int k;
    private Standardize encoder;
    private KnnAlgorithm knn;
    public KnnClassifier(int k){
        this.k=k;
    }
    public void train(DataSet trainset){
        this.encoder=new Standardize();
        encoder.fit(trainset);
        //把所有样本都转化为double[][]的形式，方便调用knn方法
        int row=trainset.getSamplelist().size();
        int line=encoder.getOutputFeaturecount();
        double[][] samples=new double[row][line];
        for (int i = 0; i < trainset.getSamplelist().size(); i++) {
            samples[i]=encoder.transform(trainset.getSample(i));
        }
        //创建样本标签序列
        int[] labels=new int[trainset.getSamplelist().size()];
        for (int i = 0; i < trainset.getSamplelist().size(); i++) {
            labels[i]=trainset.getSample(i).getLabel();
        }
        this.knn=new KnnAlgorithm(samples,labels,k);
    }
    public int predict(Sample sample){
        double[] encodersample=encoder.transform(sample);
        return knn.predict(encodersample);
    }

}
