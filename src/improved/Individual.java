/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package improved;

/**
 *
 * @author jw2-gouldcoate
 */
public class Individual 
{
    private int geneNum;
    private double[] gene;
    private int fitness;

    /**
     * Constructor for individual
     * @param n the length of genes
     */
    public Individual(int n) 
    {
        this.geneNum = n;
        this.gene  = new double[n];
    }

    public double[] getGenes() 
    {
        return gene;
    }

    public void setGenes(double[] gene) 
    {
        this.gene = gene;
    }
    
    public void setGene(int n, double value)
    {
        this.gene[n] = value;
    }

    public double getGene(int i)
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

    @Override
    public String toString()
    {
        String r = "";
        for (int i = 0; i < gene.length; i++)
        {
            r += this.getGene(i) + " ";
            
        }
        return r;
    }
    
    public Individual(Individual i)
    {
        this.fitness = i.fitness;
        this.geneNum = i.geneNum;
        this.gene = new double[geneNum];
        for (int j = 0; j < gene.length; j++)
        {
            this.gene[j] = i.gene[j];
            
        }
    }
    
}
