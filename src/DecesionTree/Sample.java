package DecesionTree;

public class Sample {
    //仅限整数类样本使用
    private int[] attributevalue;
    private int label;

    public Sample(int[] attributevalue, int label) {
        this.attributevalue = attributevalue;
        this.label = label;
    }

    public Sample() {};

    public int[] getAttributevalue() {
        return attributevalue;
    }

    public void setAttributevalue(int[] attributevalue) {
        this.attributevalue = attributevalue;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }
    public int getvalue(int a){
        //获取第a个属性取值的方法
        return attributevalue[a];
    }
}
