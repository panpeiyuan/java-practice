package Kmeans;

import java.util.ArrayList;

public class Normalization {
    private double[] means;
    private double[] standardDeviations;

    public void fit(ArrayList<KmeansSample> trainingSamples) {
        int attributeNumber = trainingSamples.get(0).getAttribute().length;
        means = new double[attributeNumber];
        standardDeviations = new double[attributeNumber];

        for (int attribute = 0; attribute < attributeNumber; attribute++) {
            double sum = 0;
            for (KmeansSample sample : trainingSamples) {
                sum += sample.getAttribute()[attribute];
            }

            double mean = sum / trainingSamples.size();
            means[attribute] = mean;

            double squaredDifferenceSum = 0;
            for (KmeansSample sample : trainingSamples) {
                double difference = sample.getAttribute()[attribute] - mean;
                squaredDifferenceSum += difference * difference;
            }

            double standardDeviation = trainingSamples.size() > 1
                    ? Math.sqrt(squaredDifferenceSum / (trainingSamples.size() - 1))
                    : 0;
            standardDeviations[attribute] =
                    standardDeviation > 1e-12 ? standardDeviation : 1;
        }
    }

    public ArrayList<KmeansSample> apply(ArrayList<KmeansSample> samples) {
        ArrayList<KmeansSample> normalizedSamples = new ArrayList<>();

        for (KmeansSample sample : samples) {
            double[] originalAttributes = sample.getAttribute();
            double[] normalizedAttributes = new double[originalAttributes.length];

            for (int attribute = 0; attribute < originalAttributes.length; attribute++) {
                normalizedAttributes[attribute] =
                        (originalAttributes[attribute] - means[attribute])
                                / standardDeviations[attribute];
            }

            normalizedSamples.add(
                    new KmeansSample(normalizedAttributes, sample.getLabel()));
        }

        return normalizedSamples;
    }
}
