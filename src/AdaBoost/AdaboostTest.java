package AdaBoost;

import java.io.File;
import java.util.ArrayList;

public class AdaboostTest {
    public static void main(String[] args) throws Exception {
        File file=new File("D:/算法学习测试数据集/CSV/iri.csv");
        ArrayList<AdaBoostSample> sampelist= CsvReader.csvreader(file);
        DataSet dataSet=new DataSet(sampelist);
        //构建决策树和最大训练轮数
        int maxtraincounts=20;
        C45DecisionTReeStump Decisiontree=new C45DecisionTReeStump(dataSet);
        OVRAdaboostAlgorithm ovrAdaboostmodel=new OVRAdaboostAlgorithm(Decisiontree,maxtraincounts);

        //对模型进行训练
        OvrAdaboostSample ovrAdaboostSample=ovrAdaboostmodel.train(sampelist);

        //用训练得到的模型进行预测
        int correctnum=0;
        for (AdaBoostSample testsample : sampelist) {
            int prelabel=ovrAdaboostmodel.predict(testsample,ovrAdaboostSample);
            int truelael=testsample.getLabel();
            if(prelabel==truelael){
                correctnum++;
            }
        }
        System.out.println("训练轮数为"+maxtraincounts+"轮,"+"样本总数为"+sampelist.size()+","+"预测正确了"+correctnum+"个,"+"准确率为"+(double)correctnum/sampelist.size());
    }
}
