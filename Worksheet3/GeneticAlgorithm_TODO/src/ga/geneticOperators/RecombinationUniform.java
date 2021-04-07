package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.Individual;
import ga.Problem;

public class RecombinationUniform <I extends Individual, P extends Problem<I>> extends Recombination<I, P> {

    public RecombinationUniform(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {
        //TODO
    }

    @Override
    public String toString(){
        return "uniform recombination (" + probability + ")";
    }
}
