package knapsack;

import experiments.*;
import ga.GAListener;
import ga.GeneticAlgorithm;
import ga.geneticOperators.*;
import ga.selectionMethods.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import statistics.StatisticBestAverage;
import statistics.StatisticBestInRun;

public class KnapsackExperimentsFactory extends ExperimentsFactory {

    private int populationSize;
    private int maxGenerations;
    private SelectionMethod<KnapsackIndividual, Knapsack> selection;
    private Recombination<KnapsackIndividual, Knapsack> recombination;
    private Mutation<KnapsackIndividual, Knapsack> mutation;
    private Knapsack problem;
    private Experiment<KnapsackExperimentsFactory, Knapsack> experiment;

    public KnapsackExperimentsFactory(File configFile) throws IOException {
        super(configFile);
    }

    @Override
    public Experiment buildExperiment() throws IOException {
        numRuns = Integer.parseInt(getParameterValue("Runs"));
        populationSize = Integer.parseInt(getParameterValue("Population_size"));
        maxGenerations = Integer.parseInt(getParameterValue("Max_generations"));

        //SELECTION
        switch (getParameterValue("Selection")) {
            case "tournament":
                int tournamentSize = Integer.parseInt(getParameterValue("Tournament_size"));
                selection = new Tournament<>(populationSize, tournamentSize);
                break;
            case "roulette_wheel":
                selection = new RouletteWheel<>(populationSize);
        }

        //RECOMBINATION
        double recombinationProbability = Double.parseDouble(getParameterValue("Recombination_probability"));
        switch (getParameterValue("Recombination")) {
            case "one_cut":
                recombination = new RecombinationOneCut<>(recombinationProbability);
                break;
            case "two_cuts":
                recombination = new RecombinationTwoCuts<>(recombinationProbability);
                break;
            case "uniform":
                recombination = new RecombinationUniform<>(recombinationProbability);
        }

        //MUTATION
        double mutationProbability = Double.parseDouble(getParameterValue("Mutation_probability"));
        if (getParameterValue("Mutation").equals("binary")) {
            mutation = new MutationBinary<>(mutationProbability);
        }

        //PPROBABILILTY OF 1S AND FITNESS TYPE
        double probabilityOf1s = Double.parseDouble(getParameterValue("Probability_of_1s"));
        int fitnessType = Integer.parseInt(getParameterValue("Fitness_type"));

        //PROBLEM
        problem = Knapsack.buildKnapsack(new File(getParameterValue("Problem_file")));
        problem.setProb1s(probabilityOf1s);
        problem.setFitnessType(fitnessType);

        String experimentTextualRepresentation = buildExperimentTextualRepresentation();
        String experimentHeader = buildExperimentHeader();
        String experimentConfigurationValues = buildExperimentValues();

        experiment = new Experiment<>(
                this,
                numRuns,
                problem,
                experimentTextualRepresentation,
                experimentHeader,
                experimentConfigurationValues);

        statistics = new ArrayList<>();
        for (String statisticName : statisticsNames) {
            ExperimentListener statistic = buildStatistic(statisticName, experimentHeader);
            statistics.add(statistic);
            experiment.addExperimentListener(statistic);
        }

        return experiment;
    }

    @Override
    public GeneticAlgorithm generateGAInstance(int seed) {
        GeneticAlgorithm<KnapsackIndividual, Knapsack> ga;

        ga = new GeneticAlgorithm<>(
                populationSize,
                maxGenerations,
                selection,
                recombination,
                mutation,
                new Random(seed));

        for (ExperimentListener statistic : statistics) {
            ga.addGAListener((GAListener) statistic);
        }

        return ga;
    }

    private ExperimentListener buildStatistic(
            String statisticName,
            String experimentHeader) {
        if (statisticName.equals("BestIndividual")) {
            return new StatisticBestInRun(experimentHeader);
        }
        if (statisticName.equals("BestAverage")) {
            return new StatisticBestAverage(numRuns, experimentHeader);
        }
        return null;
    }

    private String buildExperimentTextualRepresentation() {
        StringBuilder sb = new StringBuilder();
        sb.append("Population size:" + populationSize + "\r\n");
        sb.append("Max generations:" + maxGenerations + "\r\n");
        sb.append("Selection:" + selection + "\r\n");
        sb.append("Recombination:" + recombination + "\r\n");
        sb.append("Recombination prob.: " + recombination.getProbability() + "\r\n");
        sb.append("Mutation:" + mutation + "\r\n");
        sb.append("Mutation prob.: " + mutation.getProbability());
        return sb.toString();
    }
    private String buildExperimentHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("Population size:" + "\t");
        sb.append("Max generations:" + "\t");
        sb.append("Selection:" + "\t");
        sb.append("Recombination:" + "\t");
        sb.append("Recombination prob.:" + "\t");
        sb.append("Mutation:" + "\t");
        sb.append("Mutation prob.:" + "\t");
        return sb.toString();
    }

    private String buildExperimentValues() {
        StringBuilder sb = new StringBuilder();
        sb.append(populationSize + "\t");
        sb.append(maxGenerations + "\t");
        sb.append(selection + "\t");
        sb.append(recombination + "\t");
        sb.append(recombination.getProbability() + "\t");
        sb.append(mutation + "\t");
        sb.append(mutation.getProbability() + "\t");
        return sb.toString();
    }
}
