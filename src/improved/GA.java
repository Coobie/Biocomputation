/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package improved;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author jw2-gouldcoate
 */
public class GA
{

    private static final int popSize = 1000; //Population size
    private static final int geneNum = 40; //Number of genes each individual has
    private static final int numGen = 10000; //Number of generations to perform GA
    private static final int tournSize = Math.floorDiv(popSize, 2); //The size of the tournament 
    private static final double mutationRate = 0.03; //Rate of mutation (0.01 = 1%)
    private static final double crossRate = 0.85; //Chance of crossover (0.6 = 60%)
    private static final int ruleLength = 7; //Length of rules and datast

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        
        Population pop = new Population(popSize, tournSize, ruleLength);

        //Generate intial population       
        ArrayList<Individual> temp = new ArrayList<>();
        for (int i = 0; i < popSize; i++)
        {
            Individual ind = new Individual(geneNum);
            for (int j = 0; j < geneNum; j++)
            {
                //Random random = new Random(88595); // Seeded
                Random random = new Random(); //None seeded 
                int r = 0;

                if ((j % ruleLength == 0) || (j == geneNum - 1))
                {
                    r = random.nextInt(2);
                } else
                {
                    r = random.nextInt(3);
                }

                ind.setGene(j, r);
            }
            temp.add(ind);
        }
        pop.setPopulation(temp);

        //Print original stuff
        System.out.println("Initial Population");
        pop.evaluate();
        pop.calculateValues(0);
        pop.outputPopulation();
        System.out.println(pop.getPopulation().get(pop.getPopulation().size() - 1));

        //GA
        for (int i = 1; i <= numGen; i++)
        {
            System.out.println("generation" + " " + i);
            //Perform stuff 
            //Crossover
            //pop.performCrossoverAll(crossRate); 
            ArrayList<Individual> offSprings = pop.performCrossover(crossRate);
            //Mutation
            pop.performMutation(mutationRate, offSprings);
            //evaulate
            pop.evaluate();
            //Tournament
            pop.performTournament();
            //Output stats
            pop.calculateValues(i);
        }

        pop.outputPopulation();
        System.out.println("----");
        System.out.println("Best:");
        System.out.println(pop.getBest() + "  " + pop.getBest().getFitness());
        
    }

}
