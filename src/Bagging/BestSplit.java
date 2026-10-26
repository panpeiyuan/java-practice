package Bagging;

public class BestSplit {
    private int bestattributeindex;//最大增益率对应的属性
    private double bestthreshold;//最大增益率对应的阈值
    private double bestgainratio;//最大增益率

    public BestSplit(int bestattributeindex, double bestthreshold, double bestgainratio) {
        this.bestattributeindex = bestattributeindex;
        this.bestthreshold = bestthreshold;
        this.bestgainratio = bestgainratio;
    }

    public int getBestattributeindex() {
        return bestattributeindex;
    }

    public void setBestattributeindex(int bestattributeindex) {
        this.bestattributeindex = bestattributeindex;
    }

    public double getBestthreshold() {
        return bestthreshold;
    }

    public void setBestthreshold(double bestthreshold) {
        this.bestthreshold = bestthreshold;
    }

    public double getBestgainratio() {
        return bestgainratio;
    }

    public void setBestgainratio(double bestgainratio) {
        this.bestgainratio = bestgainratio;
    }
}
