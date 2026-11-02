package ClassificationAlgorithm.evaluation;

public class CrossValidationResult {
    private double[] accuracies;//存10x10折一共100个准确率
    private double meanAccuracy;//100个准确率求出的一个平均值
    private double standardDeviation;//100个准确率求出的一个标准差

    public CrossValidationResult(double[] accuracies) {
        if (accuracies == null || accuracies.length == 0) {
            throw new IllegalArgumentException("准确率数组不能为空");
        }

        this.accuracies = accuracies.clone();

        double sum = 0.0;
        for (double accuracy : this.accuracies) {
            sum += accuracy;
        }
        meanAccuracy = sum / this.accuracies.length;

        double squaredDifferenceSum = 0.0;
        for (double accuracy : this.accuracies) {
            double difference = accuracy - meanAccuracy;
            squaredDifferenceSum += difference * difference;
        }
        standardDeviation =
            Math.sqrt(squaredDifferenceSum / this.accuracies.length);
    }

    public double[] getAccuracies() {
        return accuracies.clone();
    }

    public double getMeanAccuracy() {
        return meanAccuracy;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }
}
