package ClassificationAlgorithm.evaluation;

import ClassificationAlgorithm.Algorithm.KnnClassifier;
import ClassificationAlgorithm.Data.DataSet;
import ClassificationAlgorithm.Data.Sample;

import java.util.ArrayList;

public class KnnCrossValidator {
    private final DataSet dataSet;
    private final int neighborCount;
    private final int foldCount;
    private final int repeatCount;
    private final long baseSeed;

    public KnnCrossValidator(
        DataSet dataSet,
        int neighborCount,
        int foldCount,
        int repeatCount,
        long baseSeed
    ) {
        this.dataSet = dataSet;
        this.neighborCount = neighborCount;
        this.foldCount = foldCount;
        this.repeatCount = repeatCount;
        this.baseSeed = baseSeed;
    }

    public CrossValidationResult run() {
        double[] accuracies = new double[repeatCount * foldCount];
        int accuracyIndex = 0;

        for (int repeat = 0; repeat < repeatCount; repeat++) {
            StratifiedKFold folds =
                new StratifiedKFold(dataSet, foldCount, baseSeed + repeat);

            for (int fold = 0; fold < foldCount; fold++) {
                int[] trainIndices = folds.trainIndex(fold);
                int[] testIndices = folds.testIndex(fold);

                ArrayList<Sample> trainSamples =
                    collectSamples(trainIndices);
                ArrayList<Sample> testSamples =
                    collectSamples(testIndices);

                DataSet trainSet =
                    new DataSet(dataSet.getSchema(), trainSamples);
                DataSet testSet =
                    new DataSet(dataSet.getSchema(), testSamples);

                KnnClassifier classifier =
                    new KnnClassifier(neighborCount);
                classifier.train(trainSet);

                int correct = 0;
                for (int i = 0; i < testSet.getSamplecount(); i++) {
                    Sample sample = testSet.getSample(i);
                    if (classifier.predict(sample) == sample.getLabel()) {
                        correct++;
                    }
                }

                accuracies[accuracyIndex] =
                    (double) correct / testSet.getSamplecount();
                accuracyIndex++;
            }
        }

        return new CrossValidationResult(accuracies);
    }

    private ArrayList<Sample> collectSamples(int[] indices) {
        ArrayList<Sample> samples = new ArrayList<>();
        for (int index : indices) {
            samples.add(dataSet.getSample(index));
        }
        return samples;
    }
}
