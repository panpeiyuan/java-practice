package Bayes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BayesAlgorithm {
    private DataSet dataSet;
    private Map<Integer,Double> priors;
    private Map<Integer,ArrayList<Map<Integer,Double>>> condprobs;

    public BayesAlgorithm(DataSet dataSet) {
        this.dataSet = dataSet;
    }


    public Map<Integer,Double> P_Yck(DataSet ds){
        //计算先验概率P(y=ck),所有类别的先验概率存在一个map里
        Map<Integer,Double> P_Yck=new HashMap<>();
        Set<Integer> labelset=ds.getLabelscount().keySet();
        for (Integer i : labelset) {
            double p=(double)ds.getLabelscount().get(i)/ds.getSamplelist().size();
            P_Yck.put(i,p);
        }
        return P_Yck;
    }

    public Map<Integer,ArrayList<Map<Integer,Double>>> P_Xajl_Yck(DataSet ds){
        Map<Integer, ArrayList<Map<Integer,Integer>>> P_Xail_Yck_num=new HashMap<>();
        Set<Integer> labelset=ds.getLabelscount().keySet();//把所有标签存入一个set数组(MAp的遍历）
        for (Integer i : labelset) {//遍历所有类别
            ArrayList<Map<Integer,Integer>> attlist=new ArrayList<>();
            for(int i1=0;i1< ds.getAttnumber();i1++){//遍历每一个类别的所有属性
                Map<Integer,Integer> attnum=new HashMap<>();
                for (Integer integer : ds.getDomain(i1)) {
                    attnum.put(integer,1);
                }
                for (Bayessample bayessample : ds.getSamplelist()) {
                    if(bayessample.getLabel()==i){
                        int v=bayessample.getvalue(i1);
                        attnum.put(v,attnum.get(v)+1);
                    }
                }
                attlist.add(attnum);
            }
            P_Xail_Yck_num.put(i,attlist);//得到一个由类别为key，对应的value为每个类别的属性列表，arraylist第i个位置就是第i个属性，i位置map内部的加入的是第j个取值为key，value是样本个数
        }
        Map<Integer,ArrayList<Map<Integer,Double>>> P_Xail_Yck_prb=new HashMap<>();
        for (Integer i : labelset) {//遍历所有类别取值
            int label_i_num=ds.getLabelscount().get(i);//得到当前类别的总个数
            ArrayList<Map<Integer,Double>> arrprb=new ArrayList<>();
            for(int att=0;att<ds.getAttnumber();att++){//遍历所有属性的种类
                Map<Integer,Double> attprb=new HashMap<>();
                for (Integer integer : ds.getDomain(att)) {//遍历每个属性的所有取值
                    //构建一个map<>，key为属性的取值，value为在类别为i的条件下，每个属性取值的概率
                    attprb.put(integer,(double)P_Xail_Yck_num.get(i).get(att).get(integer)/(label_i_num+ds.getDomain(att).size()));
                }
                arrprb.add(attprb);//arrprb为一个集合，集合里第i个位置代表第i个属性
            }
            P_Xail_Yck_prb.put(i,arrprb);//以第i个类为key，以类别为i的属性取值概率的集合为value
        }
        return P_Xail_Yck_prb;
    }

    public void train(){
        //得出先验概率和条件概率的取值表格
        priors=P_Yck(dataSet);
        condprobs=P_Xajl_Yck(dataSet);
    }
    public int predict(Bayessample sample){
        double bestscore=Double.NEGATIVE_INFINITY;//最高分取负无穷
        int bestclass=-1;//最有可能的类取-1
        Set<Integer> preYck=priors.keySet();
        for (Integer ck : preYck) {
            double score=Math.log(priors.get(ck));
            for(int j=0;j< dataSet.getAttnumber();j++){
                int v=sample.getvalue(j);
                score+= Math.log(condprobs.get(ck).get(j).get(v));
            }
            if(score>bestscore){//平票是选择先遍历到的类别
                bestscore=score;
                bestclass=ck;
            }
        }
        return bestclass;
    }
}
