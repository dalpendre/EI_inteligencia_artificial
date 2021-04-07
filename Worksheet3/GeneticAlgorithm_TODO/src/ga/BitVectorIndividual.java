package ga;

public abstract class BitVectorIndividual <P extends Problem, I extends BitVectorIndividual> extends Individual<P, I>{

    protected boolean[] genome;

    public BitVectorIndividual(P problem, int size, double prob1s) {
        super(problem);
        genome = new boolean[size];
    }

    public BitVectorIndividual(BitVectorIndividual<P, I> original) {
        super(original);
        this.genome = new boolean[original.genome.length];
        System.arraycopy(original.genome, 0, genome, 0, genome.length);
    }

    @Override
    public int getNumGenes() {
        return genome.length;
    }

    public boolean getGene(int index) {
        return genome[index];
    }

    public void setGene(int index, boolean value) {
        genome[index] = value;
    }

    @Override
    public void swapGenes(BitVectorIndividual other, int index) {
        boolean aux = genome[index];
        genome[index] = other.genome[index];
        other.genome[index] = aux;
    }
}
