package knapsack;

import ga.BitVectorIndividual;
import ga.GeneticAlgorithm;

public class KnapsackIndividual extends BitVectorIndividual <Knapsack, KnapsackIndividual>{

    private double value;
    private double weight;

    public KnapsackIndividual(Knapsack problem, int size, double prob1s){
        super(problem, size, prob1s);
        for(int i = 0; i < genome.length; i++)
        {
            genome[i] = GeneticAlgorithm.random.nextDouble() < prob1s;
        };
    }

    public KnapsackIndividual(KnapsackIndividual original) {
        super(original);
        this.weight = original.weight;
        this.value = original.value;
    }

    @Override
    public double computeFitness() {
        value = weight = 0;
        for (int i = 0; i < genome.length; i++) {
            if (genome[i]) {
                value += problem.getItem(i).value;
                weight += problem.getItem(i).weight;
            }
        }

        switch (problem.getFitnessType()) {
            case Knapsack.SIMPLE_FITNESS:
                fitness = (weight > problem.getMaximumWeight()) ? 0 : value;
                break;
            case Knapsack.PENALTY_FITNESS: //cannot be used with roulette wheel because fitness value may become negative
                double penalty = 0;
                if (weight > problem.getMaximumWeight()) {
                    penalty = problem.getMaxVP() * (weight - problem.getMaximumWeight());
                }
                fitness = value - penalty;
        }

        return fitness;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nWeight: " + weight + " (limit: " + problem.getMaximumWeight() + ")");
        sb.append("\nValue: " + value);
        sb.append("\nfitness: " + fitness);
        sb.append("\nItems: ");
        for (int i = 0; i < genome.length; i++) {
            if (genome[i]) {
                sb.append(problem.getItem(i));
            }
        }
        return sb.toString();
    }

    /**
     *
     * @param i
     * @return 1 if this object is BETTER than i, -1 if it is WORST than I and 0, otherwise.
     */
    @Override
    public int compareTo(KnapsackIndividual i){
        return this.fitness == i.getFitness()? 0 : this.fitness > i.getFitness()? 1 : -1;
    }

    @Override
    public KnapsackIndividual clone() {
        return new KnapsackIndividual(this);
    }
}
