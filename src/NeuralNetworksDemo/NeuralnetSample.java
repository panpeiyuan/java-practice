package NeuralNetworksDemo;

public class NeuralnetSample {
    private double[] attributevalue;
    private int label;

    public NeuralnetSample() {};

    public NeuralnetSample(double[] attributevalue, int label) {
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
