package DecesionTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DecisionTree {
    private DataSet dataset;

    public DecisionTree(DataSet dataset) {
        this.dataset = dataset;
    }

    public Map<Integer,Integer> countLabels(ArrayList<Integer> list){
        Map<Integer,Integer> hm=new HashMap<>();
        for (Integer i : list) {
            int label=dataset.get(i).getLabel();
            if(hm.containsKey(label)){
                int count=hm.get(label);
                count++;
                hm.put(label,count);
            }else{
                hm.put(label,1);
            }
        }
        return hm;
    }
    public double entropy(ArrayList<Integer> list){
        Map<Integer,Integer> hm=countLabels(list);
        int ASN=list.size();
        double entropy=0;
        Set<Integer> key=hm.keySet();
        for (Integer i : key) {
            double Pi=(double)hm.get(i)/ASN;
            entropy+=Pi*Math.log(Pi)/Math.log(2);
        }
        return -entropy;
    }
}
