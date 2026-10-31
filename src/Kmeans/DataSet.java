package Kmeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataSet {
    private ArrayList<KmeansSample> samplelist;//样本集（sample类两个属性：一个是存放属性的数组attribute，一个是存放标签的label）
    private int attnumber;//属性个数
    private Map<Integer,Integer> labelscount;//每个类别有多少个，放类别的取值集合

    public ArrayList<KmeansSample> getSamplelist() {
        return samplelist;
    }

    public void setSamplelist(ArrayList<KmeansSample> samplelist) {
        this.samplelist = samplelist;
    }

    public int getAttnumber() {
        return attnumber;
    }

    public Map<Integer, Integer> getLabelscount() {
        return labelscount;
    }


    public DataSet(ArrayList<KmeansSample> samplelist) {
        this.samplelist = samplelist;
        labelscount=new HashMap<>();
        attnumber=samplelist.get(0).getAttribute().length;
        //求每个类别有多少个样本，先遍历samplelist，有索引可以用增强for
        for (KmeansSample Kmeanssample : samplelist) {
            int label=Kmeanssample.getLabel();
            if(labelscount.containsKey(label)){
                int count=labelscount.get(label);
                count++;
                labelscount.put(label,count);
            }else{
                labelscount.put(label,1);
            }
        }
    }
}
