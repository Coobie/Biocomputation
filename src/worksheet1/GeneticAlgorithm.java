/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worksheet1;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author jw2-gouldcoate
 */
public class GeneticAlgorithm 
{

    /**
     * @param args the command line arguments
     */
    
    private void workout(ArrayList<Individual> individuals)
    {
        int total = 0;
        for (Individual individual : individuals) 
        {
            total += individual.getFitness();
        }
        
        System.out.println("Total = "+total);
        System.out.println("Avg = "+total/individuals.size());
    }
    
    public static void main(String[] args) 
    {
        int popSize = 100;
        int geneNum = 5;
        int numGen = 1;
        ArrayList<Individual> population = new ArrayList<>();
        
        for (int i = 0; i < popSize; i++) 
        {
            Individual temp = new Individual(geneNum);
            
            for (int j = 0; j < geneNum; j++) 
            {
                Random random = new Random();
                int r = random.nextInt(2);
                temp.setGene(j, r);
            }
            temp.setFitness(0);
            population.add(temp);
        }
        
        
        //Evaulation
        for (int i = 0; i < popSize; i++) 
        {
            for (int j = 0; j < geneNum; j++) 
            {
                if (population.get(i).getGene(j) == 1)
                {
                    population.get(i).setFitness(population.get(i).getFitness() + 1);
                }
            }
            
        }
        
        for (Individual i : population) 
        {
            for (int j = 0; j < geneNum; j++) 
            {
                System.out.print(i.getGene(j));
            }
            System.out.print("  "+i.getFitness());
            System.out.println("");
        }
        
        ArrayList<Individual> offSprings = new ArrayList<>();
        for (int i = 0; i < numGen; i++) 
        {
            for (int j = 0; j < popSize; j++) 
            {
                Random random = new Random();
                int parent1 = random.nextInt(popSize);
                int parent2 = random.nextInt(popSize);
                int[] offSpring = new int[geneNum];
                if (population.get(parent1).getFitness() >= population.get(parent2).getFitness())
                {
                    offSpring = population.get(parent1).getGenes();
                }
                else
                {
                    offSpring = population.get(parent2).getGenes();
                }
                Individual temp = new Individual(geneNum);
                temp.setGenes(offSpring);
                offSprings.add(temp);
            }
            
        }
        
        for (int i = 0; i < popSize; i++) 
        {
            for (int j = 0; j < geneNum; j++) 
            {
                if (offSprings.get(i).getGene(j) == 1)
                {
                    offSprings.get(i).setFitness(offSprings.get(i).getFitness() + 1);
                }
            }
            
        }
        
        
        int total = 0;
        for (Individual individual : population) 
        {
            total += individual.getFitness();
        }
        System.out.println("Original pop");
        System.out.println("Total = "+total);
        System.out.println("Avg = "+total/population.size());
        
        total = 0;
        for (Individual individual : offSprings) 
        {
            total += individual.getFitness();
        }
        System.out.println("OffSprings pop");
        System.out.println("Total = "+total);
        System.out.println("Avg = "+total/offSprings.size());
        
    }
    
}
