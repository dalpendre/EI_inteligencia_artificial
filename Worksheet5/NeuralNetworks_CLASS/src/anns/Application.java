package anns;

import anns.layers.*;
import anns.mlp.MLPNetwork;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Application {

    public static void main(String[] args) {

        LinkedList<Example> trainingSet = null;
        LinkedList<Example> testSet = null;
        try {
            trainingSet = readDatasetFile(new File("train_dataset.txt"));
            testSet = readDatasetFile(new File("test_dataset.txt"));
        }catch(IOException e){
            e.printStackTrace();
        }

        int inputLayerSize = 35;
        int epochs = 100;
        double learningRate = 0.2;
        int seed = 1;

        MLPNetwork neuralNetwork = new MLPNetwork(inputLayerSize);
        neuralNetwork.addLayer(new ReluLayer(12));
        neuralNetwork.addLayer(new SigmoidLayer(10));
        neuralNetwork.initialize(seed);
        neuralNetwork.train(trainingSet, epochs, learningRate);
        neuralNetwork.show(testSet);
        neuralNetwork.evaluate(testSet);
    }

    public static LinkedList<Example> readDatasetFile(File file) throws IOException {
        java.util.Scanner f = new java.util.Scanner(file);
        int numExamples = f.nextInt();
        int numInputs = f.nextInt();
        int numOutputs = f.nextInt();
        LinkedList<Example> dataset = new LinkedList<>();
        for (int e = 0; e < numExamples; e++) {
            double[] instance = new double[numInputs];
            for (int i = 0; i < numInputs; i++) {
                instance[i] = f.nextInt();
            }
            double[] target = new double[numOutputs];
            for (int o = 0; o < numOutputs; o++) {
                target[o] = f.nextInt();
            }
            dataset.add(new Example(instance, target));
        }
        return dataset;
    }

}
