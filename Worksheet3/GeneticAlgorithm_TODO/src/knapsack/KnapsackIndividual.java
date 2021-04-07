package knapsack;

import ga.BitVectorIndividual;

public class KnapsackIndividual extends BitVectorIndividual <Knapsack, KnapsackIndividual>{

    private double value;
    private double weight;

    public KnapsackIndividual(Knapsack problem, int size, double prob1s){
        super(problem, size, prob1s);
    }

    public KnapsackIndividual(KnapsackIndividual original) {
        super(original);
        this.weight = original.weight;
        this.value = original.value;
    }

    @Override
    public double computeFitness() {
        //TODO
        return 0;
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
