package ga.geneticOperators;

import ga.BitVectorIndividual;
import ga.GeneticAlgorithm;
import ga.Problem;

public class MutationBinary <I extends BitVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public MutationBinary(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {
        //TODO
    }

    @Override
    public String toString(){
        return "Binary mutation (" + probability + ")";
    }
}
