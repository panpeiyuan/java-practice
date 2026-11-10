package DBSCAN;

import KFold.KFoldEncapsulation;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TenByTen {
    private static final double EPS = 2.0;
    private static final int MIN_PTS = 5;
    private static final int NOISE = -2;
    private static final int K_FOLD = 10;
    private static final int TIMES = 10;
    private static final long SEED = 20261110L;

    public static void main(String[] args) throws Exception {
        File file = new File("D:/算法学习测试数据集/CSV/win.csv");
        ArrayList<DBSCANSample> sampleList = CsvReader.csvreader(file);

        int runCount = TIMES * K_FOLD;
        double[] coverageRates = new double[runCount];
        double[] nearestCoreDistances = new double[runCount];
        int[] predictedClusterCounts = new int[runCount];
        int[] predictedNoiseCounts = new int[runCount];
        int resultIndex = 0;

        for (int t = 0; t < TIMES; t++) {
            KFoldEncapsulation kFold =
                    new KFoldEncapsulation(sampleList.size(), K_FOLD, SEED + t);

            for (int fold = 0; fold < K_FOLD; fold++) {
                int[] trainIndices = kFold.trainIndex(fold);
                int[] testIndices = kFold.testIndex(fold);

                ArrayList<DBSCANSample> trainSamples = new ArrayList<>();
                for (int index : trainIndices) {
                    trainSamples.add(sampleList.get(index));
                }

                ArrayList<DBSCANSample> testSamples = new ArrayList<>();
                for (int index : testIndices) {
                    testSamples.add(sampleList.get(index));
                }

                Normalization normalization = new Normalization();
                normalization.fit(trainSamples);
                ArrayList<DBSCANSample> normalizedTrain =
                        normalization.apply(trainSamples);
                ArrayList<DBSCANSample> normalizedTest =
                        normalization.apply(testSamples);

                DataSet trainDataSet = new DataSet(normalizedTrain);
                DBSCANAlgorithm model =
                        new DBSCANAlgorithm(trainDataSet, EPS, MIN_PTS);
                model.fit();

                int[] trainAssignments = model.getClusterAssignments();
                boolean[] corePoints = new boolean[normalizedTrain.size()];
                for (int i = 0; i < normalizedTrain.size(); i++) {
                    corePoints[i] =
                            model.findNeighbors(i).size() >= MIN_PTS;
                }

                int[] predictedLabels = new int[normalizedTest.size()];
                Arrays.fill(predictedLabels, NOISE);

                int classifiedCount = 0;
                int noiseCount = 0;
                double nearestCoreDistanceSum = 0;
                boolean foundCorePoint = false;

                for (int i = 0; i < normalizedTest.size(); i++) {
                    double nearestDistance = Double.POSITIVE_INFINITY;
                    int nearestCluster = NOISE;
                    double[] testAttributes =
                            normalizedTest.get(i).getAttribute();

                    for (int j = 0; j < normalizedTrain.size(); j++) {
                        if (!corePoints[j]) {
                            continue;
                        }

                        double distance = Distance.euclidean(
                                testAttributes,
                                normalizedTrain.get(j).getAttribute());
                        if (distance < nearestDistance) {
                            nearestDistance = distance;
                            nearestCluster = trainAssignments[j];
                        }
                    }

                    if (Double.isFinite(nearestDistance)) {
                        foundCorePoint = true;
                        nearestCoreDistanceSum += nearestDistance;
                    }

                    if (nearestDistance <= EPS) {
                        predictedLabels[i] = nearestCluster;
                        classifiedCount++;
                    } else {
                        noiseCount++;
                    }
                }

                double coverageRate =
                        (double) classifiedCount / normalizedTest.size();
                double meanNearestCoreDistance =
                        foundCorePoint
                                ? nearestCoreDistanceSum / normalizedTest.size()
                                : Double.NaN;
                int clusterCount = countClusters(predictedLabels);

                coverageRates[resultIndex] = coverageRate;
                nearestCoreDistances[resultIndex] = meanNearestCoreDistance;
                predictedClusterCounts[resultIndex] = clusterCount;
                predictedNoiseCounts[resultIndex] = noiseCount;

                System.out.printf(
                        "第%d次10折第%d折：测试覆盖率=%.6f，"
                                + "最近核心点平均距离=%.6f，"
                                + "测试簇数=%d，测试噪声数=%d%n",
                        t + 1,
                        fold + 1,
                        coverageRate,
                        meanNearestCoreDistance,
                        clusterCount,
                        noiseCount);

                resultIndex++;
            }
        }

        System.out.println("10×10运行次数:" + resultIndex);
        printSummary("测试覆盖率", coverageRates);
        printSummary("最近核心点平均距离", nearestCoreDistances);
        printIntegerSummary("测试簇数", predictedClusterCounts);
        printIntegerSummary("测试噪声数", predictedNoiseCounts);
    }

    private static int countClusters(int[] assignments) {
        Set<Integer> clusters = new HashSet<>();
        for (int assignment : assignments) {
            if (assignment != NOISE) {
                clusters.add(assignment);
            }
        }
        return clusters.size();
    }

    private static void printSummary(String metricName, double[] values) {
        int validCount = 0;
        double sum = 0;
        double minimum = Double.POSITIVE_INFINITY;
        double maximum = Double.NEGATIVE_INFINITY;

        for (double value : values) {
            if (!Double.isFinite(value)) {
                continue;
            }
            validCount++;
            sum += value;
            minimum = Math.min(minimum, value);
            maximum = Math.max(maximum, value);
        }

        if (validCount == 0) {
            System.out.println(metricName + ": 无有效结果");
            return;
        }

        double mean = sum / validCount;
        double squaredDifferenceSum = 0;
        for (double value : values) {
            if (!Double.isFinite(value)) {
                continue;
            }
            double difference = value - mean;
            squaredDifferenceSum += difference * difference;
        }
        double standardDeviation =
                Math.sqrt(squaredDifferenceSum / validCount);

        System.out.printf(
                "%s: 均值=%.6f，标准差=%.6f，最小值=%.6f，最大值=%.6f%n",
                metricName,
                mean,
                standardDeviation,
                minimum,
                maximum);
    }

    private static void printIntegerSummary(String metricName, int[] values) {
        double[] convertedValues = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            convertedValues[i] = values[i];
        }
        printSummary(metricName, convertedValues);
    }
}
