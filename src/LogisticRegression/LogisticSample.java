package LogisticRegression;

public class LogisticSample {
    private double[] attributevalue;
    private int label;

    public LogisticSample() {};

    public LogisticSample(double[] attributevalue, int label) {
        this.attributevalue = attributevalue;
        this.label = label;
    }

    public double[] getAttributevalue() {
        return attributevalue;
    }

    public int getLabel() {
        return label;
    }

    public double getvalue(int a) {
        //返回该样本第a个属性的取值
        return attributevalue[a];
    }

}
