package Bagging;

public class SplitResult {
    //根据划分的属性+阈值确定本次划分的信息增益率
    private int attributeindex;//属性
    private double threshold;//该属性下的最优阈值
    private double gain;//该阈值下的最优信息增益
    private double gainRatio;//该阈值下的最优信息增益率

    public SplitResult(double gainRatio, double gain, double threshold, int attributeindex) {
        this.gainRatio = gainRatio;
        this.gain = gain;
        this.threshold = threshold;
        this.attributeindex = attributeindex;
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

    public double getGain() {
        return gain;
    }

    public void setGain(double gain) {
        this.gain = gain;
    }

    public double getGainRatio() {
        return gainRatio;
    }

    public void setGainRatio(double gainRatio) {
        this.gainRatio = gainRatio;
    }
}
