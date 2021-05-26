package anns.mlp;

import anns.Example;
import anns.layers.Layer;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MLPNetwork {

    private int inputSize;
    private List<Layer> layers;

    public MLPNetwork(int inputSize) {
        this.inputSize = inputSize;
        this.layers = new LinkedList<>();
    }

    public void addLayer(Layer layer){
        layers.add(layer);
    }

    /**
     * Initializes the layers.
     * @param seed seed of the random number generator.
     */
    public void initialize(int seed){
        int auxSize = inputSize;
        Random random = new Random(seed);
        for (Layer layer : layers) {
            layer.initialize(auxSize, random);
            auxSize = layer.getSize();
        }
    }

    /**
     * Computes the output of the network for a given instance.
     * @param instance instance for which the output of the network should be computed.
     * @return the output vector computed by the network.
     */
    public double[] computeOutput(double[] instance)
    {
        double[] aux = instance;

        for (Layer layer : layers)
        {
            aux = layer.computeOutput(aux);
        }

        return aux;
    }

    /**
     * Train the network with the Backpropagation algorithm.
     * @param trainingSet  training set.
     * @param epochs       number of training epochs.
     * @param learningRate learning rate.
     */
    public void train(LinkedList<Example> trainingSet, int epochs, double learningRate)
    {
        for(int i = 0; i < epochs; i++)
        {
            for (Example example : trainingSet)
            {
                computeOutput(example.getInstance());
                backPropagation(example, learningRate);
            }
            System.out.println("Loss MSE: " + meanSquareError(trainingSet));
        }
    }

    /**
     * Computes the network's accuracy for a given test set.
     * @param testSet test set.
     */
    public void evaluate(LinkedList<Example> testSet){
        double numCorrectlyClassified = 0;
        for (Example example : testSet) {
            double[] output = computeOutput(example.getInstance());
            double[] target = example.getTarget();
            int i;
            for (i = 0; i < output.length; i++) {
                //first, convert output to 1s and 0s
                output[i] = (int) (output[i] + 0.5);
                if(output[i] != target[i]){
                    break;
                }
            }
            if(i == output.length){
                numCorrectlyClassified++;
            }
        }
        System.out.println("Accuracy: " + (numCorrectlyClassified / testSet.size()));
    }

    /**
     * Computes and shows the network's output for a set of examples.
     * @param examples examples.
     */
    public void show(LinkedList<Example> examples) {
        for (Example example : examples) {
            double[] output = computeOutput(example.getInstance());
            for (int i = 0; i < output.length; i++) {
                System.out.print((int) (output[i] + 0.5) + " ");
            }
            System.out.println();
            System.out.println();
        }
    }

    /**
     * Backpropagation algorithm: computes the errors for all network layers
     * and updates the network weights accordingly (private method).
     * @param example learning example for which the errors should be computed.
     * @param learningRate learning rate.
     */
    private void backPropagation(Example example, double learningRate) {
        Layer outputLayer = layers.get(layers.size() - 1);
        outputLayer.computeOutputLayerErrors(example.getTarget());
        Layer nextLayer = outputLayer;
        for (int i = layers.size()-2; i >= 0; i--)
        {
            Layer layer = layers.get(i);
            layer.computeHiddenLayerErrors(nextLayer);
            nextLayer = layer;
        }

        double[] input = example.getInstance();
        for (Layer layer : layers)
        {
            layer.updateWeights(input, learningRate);
            input = layer.getOutput();
        }
    }

    /**
     * Computes the Mean Square Error for the given examples.
     * @param examples set of examples for which we want to compute the mean square error.
     * @return the Mean Square Error for the given examples.
     */
    private double meanSquareError(LinkedList<Example> examples){
        double sum = 0;
        for (Example e : examples) {
            double[] output = computeOutput(e.getInstance());
            for (int i = 0; i < output.length; i++) {
                sum += Math.pow(e.getTargetValue(i) - output[i], 2);
            }
        }
        return sum / examples.size();
    }
}
