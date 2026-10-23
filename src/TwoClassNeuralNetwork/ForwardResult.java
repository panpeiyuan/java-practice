package TwoClassNeuralNetwork;

public class ForwardResult {
    private double[] x;//存输入
    private double[] z1;//隐藏层激活前的值
    private double[] hiddenoutput;//隐藏层经过sigmod的输出
    private double[] z2;//输出层激活前的值
    private double[] output;//最终输出

    public ForwardResult(double[] x, double[] z1, double[] hiddenoutput, double[] z2, double[] output) {
        this.x = x;
        this.z1 = z1;
        this.hiddenoutput = hiddenoutput;
        this.z2 = z2;
        this.output = output;
    }

    public double[] getX() {
        return x;
    }

    public void setX(double[] x) {
        this.x = x;
    }

    public double[] getZ1() {
        return z1;
    }

    public void setZ1(double[] z1) {
        this.z1 = z1;
    }

    public double[] getHiddenoutput() {
        return hiddenoutput;
    }

    public void setHiddenoutput(double[] hiddenoutput) {
        this.hiddenoutput = hiddenoutput;
    }

    public double[] getZ2() {
        return z2;
    }

    public void setZ2(double[] z2) {
        this.z2 = z2;
    }

    public double[] getOutput() {
        return output;
    }

    public void setOutput(double[] output) {
        this.output = output;
    }
}
