package com.perception;

public class Main {

    public static void main(String[] args)
    {
        double[][] inputs = {{1,1}, {1,-1}, {-1,1}, {-1,-1}};
        Example[] learningExamples = new Example[4];
        learningExamples[0] = new Example(inputs[0], -1);
        learningExamples[1] = new Example(inputs[1], 1);
        learningExamples[2] = new Example(inputs[2], 1);
        learningExamples[3] = new Example(inputs[3], -1);

        for (Example learningExample : learningExamples)
        {
            System.out.println(learningExample);
        }

        Perceptron p = new Perceptron();
        p.learn(learningExamples, 0.25, 1);

        for (Example learningExample : learningExamples)
        {
            System.out.println(learningExample.getInput(0) + " OR " + learningExample.getInput(1)
            + " = " + p.run(learningExample.getInputs()));
        }
    }
}
