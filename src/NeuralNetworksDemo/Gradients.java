package NeuralNetworksDemo;

public class Gradients {
    //存各个参数的梯度
    private int inputUnitCount;
    private int hidenUnitCount;
    private int outputUnitCount;
    private double[][] GradW1;
    private double[][] GradW2;
    private double[] GradB1;
    private double[] GradB2;

    public Gradients(int inputUnitCount, int hidenUnitCount, int outputUnitCount) {
        this.inputUnitCount = inputUnitCount;
        this.hidenUnitCount = hidenUnitCount;
        this.outputUnitCount = outputUnitCount;

        GradW1=new double[hidenUnitCount][inputUnitCount];
        GradW2=new double[outputUnitCount][hidenUnitCount];
        GradB1=new double[hidenUnitCount];
        GradB2=new double[outputUnitCount];
    }

    public double[][] getGradW1() {
        return GradW1;
    }

    public void setGradW1(double[][] gradW1) {
        GradW1 = gradW1;
    }

    public double[][] getGradW2() {
        return GradW2;
    }

    public void setGradW2(double[][] gradW2) {
        GradW2 = gradW2;
    }

    public double[] getGradB1() {
        return GradB1;
    }

    public void setGradB1(double[] gradB1) {
        GradB1 = gradB1;
    }

    public double[] getGradB2() {
        return GradB2;
    }

    public void setGradB2(double[] gradB2) {
        GradB2 = gradB2;
    }
}
