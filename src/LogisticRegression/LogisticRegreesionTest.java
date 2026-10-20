package LogisticRegression;

import java.io.File;
import java.util.ArrayList;

public class LogisticRegreesionTest {
    public static void main(String[] args) throws Exception {
        //读取数据
        File file=new File("D:/算法学习测试数据集/CSV/win.csv");
        ArrayList<LogisticSample> samplelist=CsvReader.csvreader(file);
        //改用标准化后的样本数据进行模型的训练
        Normalization normal=new Normalization();
        ArrayList<LogisticSample> norsamplelist=normal.normalsamplelist(samplelist);
        //用标准化后的samplelist创建ds和logisticregressionalgorithm
        DataSet ds=new DataSet(norsamplelist);
        LogisticRegressionAlgorithm logalg=new LogisticRegressionAlgorithm(ds);
        logalg.parametertrain(20000,2);
        for (int i = 0; i < ds.getSamplelist().size(); i++) {
            System.out.print("第"+(i+1)+"个样本的预测标签为："+logalg.predict(ds.getSamplelist().get(i))+"  ");
            System.out.println("第"+(i+1)+"个样本的真实标签为："+ds.getSamplelist().get(i).getLabel());
        }
    }
}
