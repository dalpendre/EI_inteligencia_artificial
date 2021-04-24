package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.Individual;
import ga.Problem;

public class RecombinationUniform <I extends Individual, P extends Problem<I>> extends Recombination<I, P> {

    public RecombinationUniform(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2)
    {
        for (int i = 0; i < ind1.getNumGenes(); i++)
        {
            if(GeneticAlgorithm.random.nextDouble() <= probability)
            {
                ind1.swapGenes(ind2, i);
            }
        }
    }

    @Override
    public String toString(){
        return "uniform recombination (" + probability + ")";
    }
}
