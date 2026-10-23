package TwoClassNeuralNetwork;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class DataSet {
    private ArrayList<NeuralnetSample> samplelist;//样本集（sample类两个属性：一个是存放属性的集合attributevalue，一个是存放标签的label）
    private int attnumber;//属性个数
    private TreeMap<Integer,Integer> labelscount;//每个类别有多少个，放类别的取值集合

    public DataSet(ArrayList<NeuralnetSample> samplelist) {
        this.samplelist = samplelist;
        labelscount=new TreeMap<>();
        attnumber=samplelist.get(0).getAttributevalue().length;
        for (NeuralnetSample s : samplelist) {//求每个类别有多少个样本，先遍历samplelist，有索引可以用增强for
            int label=s.getLabel();
            if(labelscount.containsKey(label)){
                int count=labelscount.get(label);
                count++;
                labelscount.put(label,count);
            }else{
                labelscount.put(label,1);
            }
        }
    }

    public int size(){
        return samplelist.size();
    }
    public NeuralnetSample get(int i){
        return samplelist.get(i);
    }//由下标取到样本的方法

    public int getAttnumber(){
        return attnumber;
    }//返回属性个数

    public ArrayList<NeuralnetSample> getSamplelist() {
        return samplelist;
    }//取到这一整串属性

    public Map<Integer, Integer> getLabelscount() {
        return labelscount;
    }
}
