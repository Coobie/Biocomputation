/*
 * Licensed to JGC
 */
package improved;

/**
 *
 * @author Jacob
 */
public class Rule
{
    private int[] input;
    
    private int output;

    public Rule(int[] input, int output)
    {
        this.input = input;
        this.output = output;
    }

    public int[] getInput()
    {
        return input;
    }

    public void setInput(int[] input)
    {
        this.input = input;
    }

    public int getOutput()
    {
        return output;
    }

    public void setOutput(int output)
    {
        this.output = output;
    }
    
    
    
}
