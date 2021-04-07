package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.Individual;
import ga.Problem;

public class RecombinationOneCut <I extends Individual, P extends Problem<I>> extends Recombination<I, P> {

    public RecombinationOneCut(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {
        int cut = GeneticAlgorithm.random.nextInt(ind1.getNumGenes());
        for (int i = 0; i < cut; i++) {
            ind1.swapGenes(ind2, i);
        }
    }
    
    @Override
    public String toString(){
        return "One cut recombination (" + probability + ")";
    }    
}