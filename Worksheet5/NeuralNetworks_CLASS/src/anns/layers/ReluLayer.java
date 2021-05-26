package anns.layers;

//https://stackoverflow.com/questions/32546020/neural-network-backpropagation-with-relu/32605889

public class ReluLayer extends Layer {

    public ReluLayer(int size){
        super(size);
    }

    @Override
    public double activationFunction(double input){
        return Math.max(0, input);
    }

    @Override
    public void computeOutputLayerErrors(double[] target){
        for (int i = 0; i < output.length; i++) {
            errors[i] = output[i] == 0 ? 0 : (target[i] - output[i]) * 1; //"* 1" => unnecessary => it represents the derivative
        }
    }

    @Override
    public void computeHiddenLayerErrors(Layer nextLayer){
        for (int i = 0; i < output.length; i++) {
            errors[i] = 0;
            if(output[i] != 0){
                double sum = 0;
                for (int j = 0; j < nextLayer.size; j++) {
                    sum += nextLayer.w[i][j] * nextLayer.errors[j];
                }
                errors[i] = 1 * sum; // "1 *" => unnecessary => it represents the derivative
            }
        }
    }

}
