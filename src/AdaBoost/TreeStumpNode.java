package AdaBoost;

public class TreeStumpNode {
    private int attributeindex;
    private double threshold;
    private int leftlabel;
    private int rightlabel;

    public TreeStumpNode(int attributeindex, double threshold, int leftlabel, int rightlabel) {
        this.attributeindex = attributeindex;
        this.threshold = threshold;
        this.leftlabel = leftlabel;
        this.rightlabel = rightlabel;
    }

    public int getAttributeindex() {
        return attributeindex;
    }

    public void setAttributeindex(int attributeindex) {
        this.attributeindex = attributeindex;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public int getLeftlabel() {
        return leftlabel;
    }

    public void setLeftlabel(int leftlabel) {
        this.leftlabel = leftlabel;
    }

    public int getRightlabel() {
        return rightlabel;
    }

    public void setRightlabel(int rightlabel) {
        this.rightlabel = rightlabel;
    }
}
