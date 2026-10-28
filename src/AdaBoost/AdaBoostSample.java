package AdaBoost;

public class AdaBoostSample {
    private double[] attribute;
    private int label;

    public AdaBoostSample(double[] attribute, int label) {
        this.attribute = attribute;
        this.label = label;
    }

    public AdaBoostSample() {};

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
