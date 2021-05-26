package anns.layers;

public class SigmoidLayer extends Layer {

    public SigmoidLayer(int size){
        super(size);
    }

    @Override
    public double activationFunction(double input){
        return 1 / (1 + Math.pow(Math.E, -input));
    }

    @Override
    public void computeOutputLayerErrors(double[] target){
        for(int i = 0; i < size; i++)
        {
            errors[i] = (target[i]-output[i]) * output[i] * (1-output[i]);
        }
    }

    @Override
    public void computeHiddenLayerErrors(Layer nextLayer){
        for(int i = 0; i < size; i++)
        {
            double sum = 0;
            for(int j = 0; j < nextLayer.size; j++)
            {
                sum += nextLayer.w[i][j] + nextLayer.errors[i];
            }
            errors[i] = output[i] * (1 - output[i]) * sum;
        }
    }
}
