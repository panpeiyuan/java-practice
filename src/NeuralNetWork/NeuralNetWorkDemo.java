package NeuralNetWork;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class NeuralNetWorkDemo {
    public static void main(String[] args) throws Exception {
        File file=new File("D:/算法学习测试数据集/CSV/iri.csv");
        ArrayList<NeuralnetSample> samplelist=CsvReader.csvreader(file);
        //把数据标准化
        Normalization normalmodel=new Normalization();
        normalmodel.fit(samplelist);
        ArrayList<NeuralnetSample> normalsample=normalmodel.apply(samplelist);
        //拿表转化后的数据创建dataset
        DataSet dataSet=new DataSet(normalsample);
        ForwardPropagation units=new ForwardPropagation(dataSet.getAttnumber(),3,3,3);
        double[] losefunction=units.train(dataSet,2000,0.1);
        int correct=0;
        for (NeuralnetSample norsample : normalsample) {
            int prelabel=units.predict(norsample);
            int truelabel=norsample.getLabel();
            if(prelabel==truelabel){
                correct++;
            }
        }
        System.out.println("损失函数依次为"+ Arrays.toString(losefunction));
        System.out.println("一共猜对了"+correct+"个"+"，正确率为"+(double)correct/normalsample.size());
    }

}
