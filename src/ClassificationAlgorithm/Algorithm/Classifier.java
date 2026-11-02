package ClassificationAlgorithm.Algorithm;

import ClassificationAlgorithm.Data.DataSet;
import ClassificationAlgorithm.Data.Sample;

public interface Classifier {
    //分类算法接口，规定所有分类算法必须进行的两个操作
    public void train(DataSet trainset);
    public int predict(Sample sample);
}
