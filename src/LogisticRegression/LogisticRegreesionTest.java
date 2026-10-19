package LogisticRegression;

import java.io.File;
import java.util.ArrayList;

public class LogisticRegreesionTest {
    public static void main(String[] args) throws Exception {
        File file=new File("D:/算法学习测试数据集/CSV/iri.csv");
        ArrayList<LogisticSample> samplelist=CsvReader.csvreader(file);
        DataSet ds=new DataSet(samplelist);
        LogisticRegressionAlgorithm logalg=new LogisticRegressionAlgorithm(ds);
        logalg.parametertrain(1000,0.55);
    }
}
