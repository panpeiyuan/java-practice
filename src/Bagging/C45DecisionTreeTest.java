package Bagging;

import java.io.File;
import java.util.ArrayList;

public class C45DecisionTreeTest {
    public static void main(String[] args) throws Exception {
        File file=new File("D:/算法学习测试数据集/CSV/iri.csv");
        ArrayList<BaggingSample> samplelist=CsvReader.csvreader(file);
        DataSet dataSet=new DataSet(samplelist);
        //生成C4.5树对象
        C45DecisionTree tree=new C45DecisionTree(dataSet);
        ArrayList<Integer> attributesindex=new ArrayList<>();
        for (int i = 0; i < dataSet.getAttnumber(); i++) {
            attributesindex.add(i);
        }
        //用所有样本和可用属性建树
        TreeNode rootnode=tree.buildtree(samplelist,attributesindex);
        int correctnum=0;
        for (BaggingSample sample : samplelist) {
            int prelabel= tree.predict(rootnode,sample);
            int truelabel=sample.getLabel();
            if(prelabel==truelabel){
                correctnum++;
            }
        }
        System.out.println("共预测正确了"+correctnum+"个样本"+"，正确率为"+(double)correctnum/samplelist.size());
    }
}
