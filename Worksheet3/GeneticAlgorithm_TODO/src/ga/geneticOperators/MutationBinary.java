package ga.geneticOperators;

import ga.BitVectorIndividual;
import ga.GeneticAlgorithm;
import ga.Problem;

public class MutationBinary <I extends BitVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public MutationBinary(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind)
    {
        for (int i = 0; i < ind.getNumGenes(); i++)
        {
            if(GeneticAlgorithm.random.nextDouble() <= probability)
            {
                ind.setGene(i, !ind.getGene(i));
            }
        }
    }

    @Override
    public String toString()
    {
        return "Binary mutation (" + probability + ")";
    }
}