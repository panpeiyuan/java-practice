package DBSCAN;

public class DBSCANSample {
    private double[] attribute;
    private int label;

    public DBSCANSample(double[] attribute, int label) {
        this.attribute = attribute;
        this.label = label;
    }

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
