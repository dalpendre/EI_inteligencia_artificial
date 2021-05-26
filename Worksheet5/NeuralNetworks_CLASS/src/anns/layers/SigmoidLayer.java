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
        //TODO
    }

    @Override
    public void computeHiddenLayerErrors(Layer nextLayer){
        //TODO
    }
}
