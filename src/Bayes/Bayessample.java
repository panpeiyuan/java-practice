package Bayes;

import java.util.ArrayList;

public class Bayessample {
    private ArrayList<Integer> attributevalue;
    private int label;

    public Bayessample() {
    }

    public Bayessample(ArrayList<Integer> attributevalue, int label) {
        this.attributevalue = attributevalue;
        this.label = label;
    }

    public ArrayList<Integer> getAttributevalue() {
        return attributevalue;
    }

    public void setAttributevalue(ArrayList<Integer> attributevalue) {
        this.attributevalue = attributevalue;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public int getvalue(int a) {
        //返回第a个属性的取值
        return attributevalue.get(a);
    }

}
