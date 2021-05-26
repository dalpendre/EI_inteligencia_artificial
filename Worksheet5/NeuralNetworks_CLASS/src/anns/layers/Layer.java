package anns.layers;

import java.util.Random;

public abstract class Layer{
    protected int size;
    protected int inputSize;
    protected double[][] w;
    protected double[] b;
    protected double[] output;
    protected double[] errors;

    public Layer(int size){
        this.size = size;
    }

    /**
     * Creates the weights matrix, and the bias, output and errors vectors.
     * Initializes the network and bias weights in the interval [-1, 1].
     * @param inputSize size of the layer's input vector.
     * @param random Random number generator.
     */
    public void initialize(int inputSize, Random random){
        this.inputSize = inputSize;
        w = new double[inputSize][size];
        b = new double[size];
        output = new double[size];
        errors = new double[size];

        for (int j = 0; j < size; j++) {
            for (int i = 0; i < inputSize; i++) {
                w[i][j] = random.nextDouble() * 2 - 1; //random values between -1 and 1
            }
            b[j] = random.nextDouble() * 2 - 1;
        }
    }

    /**
     * Computes the output of the layer for a given input.
     * @param input layer's input.
     * @return the layer's output vector.
     */
    public double[] computeOutput(double[] input){
        //TODO
        return output;
    }

    /**
     * Computes the activation function for a given input.
     * @param input activation function's input.
     * @return activation value.
     */
    public abstract double activationFunction(double input);

    /**
     * Computes the error vector for an output layer.
     * @param target the target output vector.
     */
    public abstract void computeOutputLayerErrors(double[] target);

    /**
     * Computes the error vector for a hidden layer.
     * @param nextLayer the errors vector computed for the next layer.
     */
    public abstract void computeHiddenLayerErrors(Layer nextLayer);

    /**
     * Updates the weights of the layer (w matrix and b vector).
     * It assumes that the errors vector has been computed.
     * @param input layer's input.
     * @param learningRate learning rate.
     */
    public void updateWeights(double[] input, double learningRate){
        //TODO
    }

    public int getSize() {
        return size;
    }

    public double[] getOutput() {
        return output;
    }
}
