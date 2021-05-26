package com.perception;

import java.util.Arrays;

public class Example
{
    private double[] inputs;
    private int target;     //Resultado input

    public Example(double[] inputs, int target)
    {
        this.inputs = new double[inputs.length];
        System.arraycopy(inputs, 0, this.inputs, 0, inputs.length);
        this.target = target;
    }

    public double[] getInputs()
    {
        return inputs;
    }

    public int getTarget()
    {
        return target;
    }

    public int getNumInputs()
    {
        return inputs.length;
    }

    public double getInput(int pos)
    {
        return inputs[pos];
    }

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append("{[");
        for(int i = 0; i < inputs.length; i++)
        {
           str.append(inputs[i]);
           if(i < inputs.length-1)
           {
                str.append(", ");
           }
        }
        str.append("], ");
        str.append(target);
        str.append("}");

        return str.toString();
    }
}
