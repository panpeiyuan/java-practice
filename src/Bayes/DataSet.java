package Bayes;


import java.util.*;

public class DataSet {
    private ArrayList<Bayessample> samplelist;//样本集（sample类两个属性：一个是存放属性的集合attributevalue，一个是存放标签的label）
    private int attnumber;//属性个数
    private Map<Integer,Integer> labelscount;//每个类别有多少个，放类别的取值集合
    private ArrayList<Set<Integer>> valuerange;//属性的取值范围，第a个位置放第a个属性的取值集合

    public DataSet(ArrayList<Bayessample> samplelist) {
        this.samplelist = samplelist;
        labelscount=new HashMap<>();
        attnumber=samplelist.get(0).getAttributevalue().size();
        valuerange=new ArrayList<>();
        for(int i=0;i<attnumber;i++){
            valuerange.add(new HashSet<Integer>());//每个属性创建一个set集合，泛型为int，存放每一个属性的所有可能取值
        }
        for (Bayessample s : samplelist) {//求每个类别有多少个样本，先遍历samplelist，有索引可以用增强for
            int label=s.getLabel();
            for(int i=0;i<attnumber;i++){
                valuerange.get(i).add(s.getvalue(i));//get（i）是调出第i个set集合，向第i个set集合中add一个属性值
            }
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
    public Bayessample get(int i){
        return samplelist.get(i);
    }
    public int getAttnumber(){
        return attnumber;
    }
    public Set<Integer> getDomain(int i){
        //返回第i个属性的取值集合
        return valuerange.get(i);
    }

    public ArrayList<Bayessample> getSamplelist() {
        return samplelist;
    }

    public Map<Integer, Integer> getLabelscount() {
        return labelscount;
    }

    public ArrayList<Set<Integer>> getValuerange() {
        return valuerange;
    }

}
