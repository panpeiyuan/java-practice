package DBSCAN;

import java.util.ArrayList;

public class Normalization {
    private double[] means;
    private double[] standardDeviations;

    public void fit(ArrayList<DBSCANSample> trainingSamples) {
        int attributeNumber = trainingSamples.get(0).getAttribute().length;
        means = new double[attributeNumber];
        standardDeviations = new double[attributeNumber];

        for (int attribute = 0; attribute < attributeNumber; attribute++) {
            double sum = 0;
            for (DBSCANSample sample : trainingSamples) {
                sum += sample.getAttribute()[attribute];
            }

            double mean = sum / trainingSamples.size();
            means[attribute] = mean;

            double squaredDifferenceSum = 0;
            for (DBSCANSample sample : trainingSamples) {
                double difference = sample.getAttribute()[attribute] - mean;
                squaredDifferenceSum += difference * difference;
            }

            double standardDeviation = trainingSamples.size() > 1 ? Math.sqrt(squaredDifferenceSum / (trainingSamples.size() - 1)) : 0;
            standardDeviations[attribute] = standardDeviation > 1e-12 ? standardDeviation : 1;
        }
    }

    public ArrayList<DBSCANSample> apply(ArrayList<DBSCANSample> samples) {
        ArrayList<DBSCANSample> normalizedSamples = new ArrayList<>();

        for (DBSCANSample sample : samples) {
            double[] originalAttributes = sample.getAttribute();
            double[] normalizedAttributes = new double[originalAttributes.length];

            for (int attribute = 0; attribute < originalAttributes.length; attribute++) {
                normalizedAttributes[attribute] = (originalAttributes[attribute] - means[attribute]) / standardDeviations[attribute];
            }

            normalizedSamples.add(new DBSCANSample(normalizedAttributes, sample.getLabel()));
        }

        return normalizedSamples;
    }
}
