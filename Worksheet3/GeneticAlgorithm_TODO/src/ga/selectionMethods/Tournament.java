package ga.selectionMethods;

import ga.GeneticAlgorithm;
import ga.Individual;
import ga.Population;
import ga.Problem;

public class Tournament <I extends Individual, P extends Problem<I>> extends SelectionMethod<I, P> {

    private int size;

    public Tournament(int popSize) {
        this(popSize, 2);
    }

    public Tournament(int popSize, int size) {
        super(popSize);
        this.size = size;
    }

    @Override
    public Population<I, P> run(Population<I, P> original) {
        Population<I, P> result = new Population<>(original.getSize());

        for (int i = 0; i < popSize; i++) {
            result.addIndividual(tournament(original));
        }
        return result;
    }

    private I tournament(Population<I, P> population) {
        //TODO
        return null;
    }

    @Override
    public String toString(){
        return "Tournament(" + size + ")";
    }
}
