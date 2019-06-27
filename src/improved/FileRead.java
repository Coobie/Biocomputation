/*
 * Licensed to JGC
 */
package improved;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jacob
 */
public class FileRead
{
    private ArrayList<double[]> input;
    private ArrayList<Double> output;
    private int rLength;

    public FileRead(int r)
    {
        this.rLength = r;
        this.input = new ArrayList<>();
        this.output = new ArrayList<>();
        File file
                    = new File("C:\\Users\\Jacob\\OneDrive\\UWE-3\\BioComputation\\Bio\\src\\txt\\data3.txt");
            Scanner sc = null;
            try
            {
                sc = new Scanner(file);
            } catch (FileNotFoundException ex)
            {
                Logger.getLogger(Population.class.getName()).log(Level.SEVERE, null, ex);
            }
            sc.nextLine();
            String line = "";
            int i = 0;
            while (sc.hasNextLine())
            {
                line = sc.nextLine();
                String[] a = line.split(" ");
                double[] input = new double[rLength]; // Length of dataset
                double output = 0;
                for (int j = 0; j < rLength; j++)
                {
                    input[j] = Double.parseDouble(a[j]); 
                }
                output = Character.getNumericValue(a[rLength].charAt(0));
 
                this.input.add(input);
                this.output.add(output);
            }
    }
    
    public FileRead()
    {
        
    }

    public FileRead(FileRead fr)
    {
        this.input = fr.input;
        this.output = fr.output;
        this.rLength = fr.rLength;
    }
    
    public double[] getInput(int i)
    {
        return input.get(i);
    }
    
    public double getOutput(int i)
    {
        return output.get(i);
    }
    
    public int getCount()
    {
        return  output.size();
    }

    public ArrayList<double[]> getInput()
    {
        return input;
    }

    public void setInput(ArrayList<double[]> input)
    {
        this.input = input;
    }

    public ArrayList<Double> getOutput()
    {
        return output;
    }

    public void setOutput(ArrayList<Double> output)
    {
        this.output = output;
    }
    
    
    
    public static void main(String[] args)
    {
        FileRead r = new FileRead(7);
        
        for (int i = 0; i < r.getCount(); i++)
        {
            System.out.print("Line:"+i+" ");
            for (double d : r.getInput(i))
            {
                System.out.print(d +" ");
            }
            System.out.print(r.getOutput(i));
            System.out.println("");
        }
    }
    
    
        
    
}
