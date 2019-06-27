/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package improved;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jw2-gouldcoate
 */
public class Population
{

    private ArrayList<Individual> population = new ArrayList<>();
    private int bestEver = -1123; //Default value - change if your best fitness is less than the default value
    private int popSize;
    private int tSize;
    private FileRead file;
    private int ruleLength;
    private Individual best;
    private int amountTestData = 600;

    private ArrayList<Double> testOut = new ArrayList<>();
    private ArrayList<double[]> testIn = new ArrayList<>();

    /**
     * Constructor for population
     *
     * @param popSize The size of the population
     * @param tSize The size of the tournament
     */
    public Population(int popSize, int tSize, int rLength)
    {
        this.popSize = popSize;
        this.tSize = tSize;
        this.ruleLength = rLength;

        this.file = new FileRead(rLength / 2);
        int i = 0;
        while (i != amountTestData)
        {
            Random r = new Random();
            int a = r.nextInt(this.file.getCount());
            this.testIn.add(this.file.getInput(a));
            this.testOut.add(this.file.getOutput(a));
            this.file.getOutput().remove(a);
            this.file.getInput().remove(a);
            i++;
        }
        //this.file = fr;
//        System.out.println("size:"+ this.file.getCount());
//        for (double[] ds : this.file.getInput())
//        {
//            for (double d : ds)
//            {
//                System.out.print(d);
//            }
//            System.out.println("");
//        }
    }

    /**
     * Gets the population
     *
     * @return
     */
    public ArrayList<Individual> getPopulation()
    {
        return population;
    }

    /**
     * Sets the population
     *
     * @param population
     */
    public void setPopulation(ArrayList<Individual> population)
    {
        this.population = population;
    }

    /**
     * Calls method for correct evaluation
     */
    public void evaluate()
    {
        //this.evaluateCountOnes();
        //this.evaulateComplex();
        this.evaulateRules();
    }

    private void evaulateRules() //Start cw
    {
        for (Individual i : population)
        {
            int n = ruleLength; //Length of rule
            i.setFitness(0);
            ArrayList<Rule> rules = new ArrayList<>();

            for (int j = 0; j < i.getGenes().length; j += n + 1)
            {
                double[] input = new double[n];
                for (int k = j; k < j + n; k++)
                {
                    input[k - j] = i.getGene(k);
                }
                double output = i.getGene(j + n);
                rules.add(new Rule(input, output));
            }

            //Rules for i calculated
            for (int j = 0; j < file.getCount(); j++)
            {
                for (Rule r : rules)
                {
                    if ((file.getInput(j)[0] > r.getInput()[0]) && (file.getInput(j)[0] < r.getInput()[1])
                            && (file.getInput(j)[1] > r.getInput()[2]) && (file.getInput(j)[1] < r.getInput()[3])
                            && (file.getInput(j)[2] > r.getInput()[4]) && (file.getInput(j)[2] < r.getInput()[5])
                            && (file.getInput(j)[3] > r.getInput()[6]) && (file.getInput(j)[3] < r.getInput()[7])
                            && (file.getInput(j)[4] > r.getInput()[8]) && (file.getInput(j)[4] < r.getInput()[9])
                            && (file.getInput(j)[5] > r.getInput()[10]) && (file.getInput(j)[5] < r.getInput()[11])
                            && (file.getInput(j)[6] > r.getInput()[12]) && (file.getInput(j)[6] < r.getInput()[13]))

                    {
                        if (r.getOutput() == file.getOutput(j))
                        {
                            i.setFitness(i.getFitness() + 1);
                        }
                        break;
                    }
                }

            }
        }
    }

    private int getComplexVal(int[] a)
    {
        int[] j = new int[a.length - 1];
        for (int i = 1; i < a.length; i++)
        {
            j[i - 1] = a[i];

        }

        int val = this.binarytoInt(j);

        if (a[0] == 1)
        {
            val *= -1;
        }

        return val;

    }

    private int binarytoInt(int[] a)
    {
        int value;
        String st = "";

        for (int i = 0; i < a.length; i++)
        {
            st += a[i];
        }

        value = Integer.parseInt(st, 2);

        return value;
    }

    /**
     * Method that calculates and then prints out the total, average and best
     * fitness for the generation
     */
    public void calculateValues(int g)
    {
        this.evaluate();
        int total = 0;
        double avg = 0.0;
        int best = population.get(0).getFitness();
        for (Individual i : population)
        {
            total += i.getFitness();
            if (i.getFitness() >= best)
            {
                best = i.getFitness();
            }
        }
        if (best >= bestEver || bestEver == -1123)
        {
            bestEver = best;
        }
        avg = total / population.size();

        System.out.println("Total: " + total);
        System.out.println("Average: " + avg);
        System.out.println("Best of current population: " + best);
        System.out.println("Best of ever: " + bestEver);
        System.out.println("-------");
        this.outputFile(g, total, avg, best);
    }

    private void outputFile(int g, int t, double avg, int best)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Output.csv", true));

            String line = g + "," + t + "," + avg + "," + best;

            if (g == 0)
            {
                writer.write("Generation, Total, Average, Best");
                writer.newLine();
                writer.append(line);
                writer.newLine();
            } else
            {
                writer.append(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException ex)
        {
            Logger.getLogger(Population.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Simple method that prints out each gene followed by its fitness
     */
    public void outputPopulation()
    {
        this.evaluate();
        for (Individual i : population)
        {
            System.out.print(i);
            System.out.print("  " + i.getFitness());
            System.out.println("");
        }
    }

    /**
     * Mutation operator
     *
     * @param pm the chance of mutation occurring per gene
     * @param list
     */
    public void performMutation(double pm, ArrayList<Individual> list)
    {
        for (Individual i : list)
        {
            for (int j = 0; j < i.getGenes().length; j++)
            {
                Random rand = new Random();

                int chance = (int) (pm * 100);
                if (chance > rand.nextInt(100))
                {
                    if (((j % ruleLength == 0) || (j == i.getGenes().length - 1)) && j != 0)
                    { //Output
                        if (i.getGene(j) == 1)
                        {
                            i.setGene(j, 0);
                        } else
                        {
                            i.setGene(j, 1);
                        }
                    } else
                    { //Input
                        Random rand2 = new Random();
                        double amt = rand2.nextInt(100000);
                        //double amt = rand2.nextInt(1000);
                        amt = amt / 1000000;
                        //amt = amt / 10000;
                        double value = 0;
                        //Plus or minus
                        int c = rand2.nextInt(2);
                        if (c == 0)
                        {
                            if (i.getGene(j) + amt > 1)
                            {
                                value = i.getGene(j) - amt;
                            } else
                            {
                                value = i.getGene(j) + amt;
                            }
                        } else
                        {
                            if (i.getGene(j) - amt < 0)
                            {
                                value = i.getGene(j) + amt;
                            } else
                            {
                                value = i.getGene(j) - amt;
                            }
                        }
                        i.setGene(j, value);

                    }

                }
            }
            population.add(i);
        }
    }

    /**
     * Crossover based on tutorial, the chance of selection is proportional to
     * fitness Find couple of current population compare and put the best
     * fitness into tournament pool
     *
     * @param px crossover chance
     * @return
     */
    public ArrayList<Individual> performCrossover(double px)
    {
        this.evaluate();

        ArrayList<Individual> selected = new ArrayList<>();
        ArrayList<Individual> allOffSprings = new ArrayList<>();

        for (int i = 0; i < tSize; i++)
        {
            //Select two randomly and keep the best
            Random rand = new Random();
            Individual p1 = population.get(rand.nextInt(population.size()));
            Individual p2 = population.get(rand.nextInt(population.size()));

            if (p1.getFitness() > p2.getFitness())
            {
                selected.add(p1);
            } else
            {
                selected.add(p2);
            }
        }

        for (Individual i : selected)
        {
            Random rand = new Random();
            int chance = (int) (px * 100);
            int[] parentIndex = new int[2];

            for (int j = 0; j < parentIndex.length; j++)
            {
                parentIndex[j] = rand.nextInt(selected.size());
            }
            if (chance > rand.nextInt(100))
            {

                ArrayList<Individual> offSprings = this.crossover(selected, parentIndex);

                for (Individual o : offSprings)
                {
                    allOffSprings.add(o);
                }

            } else
            {
                allOffSprings.add(population.get(parentIndex[0]));
                allOffSprings.add(population.get(parentIndex[1]));
            }
        }
        return allOffSprings;
    }

    /**
     * Crossover based on all parents being able to crossover (random)
     *
     * @param px crossover chance
     */
    public ArrayList<Individual> performCrossoverAll(double px)
    {
        ArrayList<Individual> allOffSprings = new ArrayList<>();
        for (int k = 0; k < tSize; k++)
        {
            Random rand = new Random();
            int chance = (int) (px * 100);
            if (chance > rand.nextInt(100))
            {
                //Select parents limited to 2 parents with one point crossover
                int[] parentIndex = new int[2];

                for (int i = 0; i < parentIndex.length; i++)
                {
                    parentIndex[i] = rand.nextInt(population.size());
                }

                ArrayList<Individual> offSprings = this.crossover(population, parentIndex);
                for (Individual o : offSprings)
                {
                    allOffSprings.add(o);
                }
            }
        }
        return allOffSprings;
    }

    /**
     * Crossover code
     *
     * @param list The ArrayList
     * @param parentIndex
     * @return
     */
    public ArrayList<Individual> crossover(ArrayList<Individual> list, int[] parentIndex)
    {
        //One point
        Random rand = new Random();
        int geneNum = list.get(0).getGenes().length;
        int point = rand.nextInt(geneNum);

        double[] child1 = new double[geneNum];
        double[] child2 = new double[geneNum];
        for (int i = 0; i < point; i++)
        {
            child1[i] = list.get(parentIndex[0]).getGene(i);
            child2[i] = list.get(parentIndex[1]).getGene(i);
        }
        for (int i = point; i < geneNum; i++)
        {
            child1[i] = list.get(parentIndex[1]).getGene(i);
            child2[i] = list.get(parentIndex[0]).getGene(i);
        }

        Individual c1 = new Individual(geneNum);
        Individual c2 = new Individual(geneNum);

        c1.setGenes(child1);
        c2.setGenes(child2);

        ArrayList<Individual> offSprings = new ArrayList<>();
        offSprings.add(c1);
        offSprings.add(c2);

        return offSprings;
    }

    public void performTournament()
    {
        this.evaluate();
        //this.randomReplace();
        this.removeWorst();
        //this.replaceWorst();
        this.evaluate();
    }

    public void removeWorst()
    {

        while (population.size() != popSize)
        {
            int worstValue = population.get(0).getFitness();
            int worstIndex = 0;

            for (int i = 0; i < population.size(); i++)
            {
                if (population.get(i).getFitness() < worstValue)
                {
                    worstValue = population.get(i).getFitness();
                    worstIndex = i;
                }
            }
            population.remove(worstIndex);
        }
    }

    public void randomReplace()
    {
        while (population.size() != popSize)
        {
            Random rand = new Random();
            int i = rand.nextInt(population.size());

//            if (population.get(i).getFitness() != this.bestEver)
//            {
//                population.remove(i);
//            }
            population.remove(i);
        }
    }

    public void replaceWorst()
    {
        List<Individual> previousPop = new ArrayList<>();

        for (Individual i : population.subList(0, popSize))
        {
            previousPop.add(new Individual(i));
        }

        List<Individual> offSpringPop = new ArrayList<>();
        for (Individual i : this.getPopulation().subList(popSize - 1, this.getPopulation().size() - 1))
        {
            offSpringPop.add(new Individual(i));
        }

        //Find best in previous
        int fitnessPrevious = -1281028;
        int indexPrevious = 0;
        int j = 0;
        for (Individual i : previousPop)
        {
            if (i.getFitness() > fitnessPrevious)
            {
                indexPrevious = j;
                fitnessPrevious = i.getFitness();
            }
            j++;
        }

        Individual previousBest = new Individual(previousPop.get(indexPrevious));

        population.clear();

        for (Individual i : offSpringPop)
        {
            population.add(new Individual(i));
        }

        int fitnessOff = 1000000;
        int indexOff = 0;
        j = 0;
        for (Individual i : population)
        {
            if (i.getFitness() < fitnessOff)
            {
                indexOff = j;
                fitnessOff = i.getFitness();
            }
            j++;
        }
        population.remove(indexOff);
        population.add(new Individual(previousBest));
    }

    public Individual getBest()
    {
        for (Individual i : population)
        {
            if (i.getFitness() == this.bestEver)
            {
                return i;
            }
        }

        return null;
    }

    public double testBest()
    {
        double acc = 0;
        Individual best = this.getBest();
        ArrayList<Rule> rules = new ArrayList<>();
        int n = ruleLength; //Length of rule
        for (int j = 0; j < best.getGenes().length; j += n + 1)
        {
            double[] input = new double[n];
            for (int k = j; k < j + n; k++)
            {
                input[k - j] = best.getGene(k);
            }
            double output = best.getGene(j + n);
            rules.add(new Rule(input, output));
        }
        int q = 0;
        
        for (int i = 0; i < this.testOut.size(); i++)
        {
            for (Rule r : rules)
                {
                    if ((this.testIn.get(i)[0] > r.getInput()[0]) && (this.testIn.get(i)[0] < r.getInput()[1])
                            && (this.testIn.get(i)[1] > r.getInput()[2]) && (this.testIn.get(i)[1] < r.getInput()[3])
                            && (this.testIn.get(i)[2] > r.getInput()[4]) && (this.testIn.get(i)[2] < r.getInput()[5])
                            && (this.testIn.get(i)[3] > r.getInput()[6]) && (this.testIn.get(i)[3] < r.getInput()[7])
                            && (this.testIn.get(i)[4] > r.getInput()[8]) && (this.testIn.get(i)[4] < r.getInput()[9])
                            && (this.testIn.get(i)[5] > r.getInput()[10]) && (this.testIn.get(i)[5] < r.getInput()[11])
                            && (this.testIn.get(i)[6] > r.getInput()[12]) && (this.testIn.get(i)[6] < r.getInput()[13]))

                    {
                        if (r.getOutput() == this.testOut.get(i))
                        {
                            q++;
                        }
                        break;
                    }
                }
        }
        
        acc = q/amountTestData;
        
        return q;
    }
}
