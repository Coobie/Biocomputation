/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worksheet1;

/**
 *
 * @author jw2-gouldcoate
 */
public class Individual 
{
    private int n;
    private int[] gene;
    private int fitness;

    public Individual(int n) 
    {
        this.n = n;
        this.gene  = new int[n];
    }

    public int[] getGenes() 
    {
        return gene;
    }

    public void setGenes(int[] gene) 
    {
        this.gene = gene;
    }
    
    public void setGene(int n, int value)
    {
        this.gene[n] = value;
    }

    public int getGene(int i)
    {
        return this.gene[i];
    }
    public int getFitness() 
    {
        return fitness;
    }

    public void setFitness(int fitness) 
    {
        this.fitness = fitness;
    }
       
    
}


