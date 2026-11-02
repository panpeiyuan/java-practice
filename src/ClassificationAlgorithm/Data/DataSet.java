package ClassificationAlgorithm.Data;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class DataSet {
    private final DataSchema schema;
    private final ArrayList<Sample> samplelist;
    private Map<Integer,Integer> labelscount;
    private int attnumber;//属性个数

    public DataSet(DataSchema schema, ArrayList<Sample> samplelist) {
        if(schema==null){
            throw new IllegalArgumentException("数据模式不能为空");
        }
        if(samplelist==null){
            throw new IllegalArgumentException("样本序列不能为空");
        }
        ArrayList<Sample> newsamplelist=new ArrayList<>();
        for (int i = 0; i < samplelist.size(); i++) {
            newsamplelist.add(samplelist.get(i));
        }
        this.schema = schema;
        this.samplelist = newsamplelist;
        attnumber=samplelist.get(0).getValues().length;
        labelscount=new TreeMap<>();
        for (Sample s : samplelist) {//求每个类别有多少个样本，先遍历samplelist，有索引可以用增强fo
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

    public DataSchema getSchema() {
        return schema;
    }

    public int getSamplecount() {
        return samplelist.size();
    }

    public Sample getSample(int index){
        return samplelist.get(index);
    }

    public Map<Integer,Integer> getlabelcount(){
        return labelscount;
    }

    public int getAttnumber() {
        return attnumber;
    }

    public Map<Integer, Integer> getLabelscount() {
        return labelscount;
    }

    public ArrayList<Sample> getSamplelist() {
        return samplelist;
    }
}
