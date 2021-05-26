package com.perception;

import java.util.Random;

public class Perceptron
{
    private double[] weights;
    private double b;   //Peso do bias

    public void learn(Example[] learningExamples, double learningRate, int seed)
    {
        weights = new double[learningExamples[0].getNumInputs()];
        weightsInitialization(seed);
        boolean change;
        do
        {
            change = false;
            for (Example learningExample : learningExamples)
            {
                double output = run(learningExample.getInputs());
                if (output != learningExample.getTarget())
                {
                    change = true;
                    for (int i = 0; i < weights.length; i++)
                    {
                        weights[i] += learningRate * (learningExample.getTarget() - output) *
                        learningExample.getInput(i);
                    }
                    b += learningRate * (learningExample.getTarget() - output) * 1;
                }
            }
        }while(change);
    }

    public int run(double[] inputs)
    {
        double weightedSum = b;
        for(int i = 0; i < inputs.length; i++)
        {
            weightedSum += inputs[i]*weights[i];
        }

        return weightedSum > 0 ? 1 : -1;
    }

    private void weightsInitialization(int seed)
    {
        Random random = new Random(seed);
        for (int i = 0; i < weights.length; i++)
        {
            weights[i] = random.nextDouble()*2-1;
        }
        b = random.nextDouble()*2-1;
    }
}
