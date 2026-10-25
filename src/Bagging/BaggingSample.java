package Bagging;

public class BaggingSample {
    private double[] attribute;
    private int label;

    public BaggingSample(double[] attribute, int label) {
        this.attribute = attribute;
        this.label = label;
    }

    public BaggingSample() {};

    public double[] getAttribute() {
        return attribute;
    }

    public void setAttribute(double[] attribute) {
        this.attribute = attribute;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }
}
