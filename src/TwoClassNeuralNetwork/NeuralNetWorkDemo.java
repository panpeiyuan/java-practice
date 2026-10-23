package TwoClassNeuralNetwork;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class NeuralNetWorkDemo {
    public static void main(String[] args) throws Exception {
        File file=new File("D:/算法学习测试数据集/CSV/iri.csv");
        ArrayList<NeuralnetSample> samples=CsvReader.csvreader(file);
        //用标准化后的数据创建dataset
        Normalization normalmodel=new Normalization();
        normalmodel.fit(samples);
        ArrayList<NeuralnetSample> normalsample=normalmodel.apply(samples);
        DataSet dataSet=new DataSet(normalsample);
        ForwardPropagation unit=new ForwardPropagation(dataSet.getAttnumber(), 3,1,333);
        double[] loselist=unit.train(dataSet,2000,0.1);
        int corrnum=0;
        for (NeuralnetSample Sample : normalsample) {
            int prelabel=unit.predict(Sample);
            if(prelabel==Sample.getLabel()){
                corrnum++;
            }
        }
        System.out.println("训练损失函数统计"+ Arrays.toString(loselist));
        System.out.println("对于整个数据集：共预测正确了"+corrnum+"个"+"，正确率为"+(double)corrnum/samples.size());
    }
}
