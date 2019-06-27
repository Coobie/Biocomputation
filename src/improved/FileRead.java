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
    private ArrayList<int[]> input;
    private ArrayList<Integer> output;
    private int rLength;

    public FileRead(int r)
    {
        this.rLength = r;
        this.input = new ArrayList<>();
        this.output = new ArrayList<>();
        File file
                    = new File("C:\\Users\\Jacob\\OneDrive\\UWE-3\\BioComputation\\Bio\\src\\txt\\data2.txt");
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
                int[] input = new int[rLength]; // Length of dataset
                int output = 0;
                for (int j = 0; j < a[0].length(); j++)
                {
                    input[j] = Character.getNumericValue(a[0].charAt(j)); 
                }
                output = Character.getNumericValue(a[1].charAt(0));
 
                this.input.add(input);
                this.output.add(output);
            }
    }

    public int[] getInput(int i)
    {
        return input.get(i);
    }
    
    public int getOutput(int i)
    {
        return output.get(i);
    }
    
    public int getCount()
    {
        return  output.size();
    }
    
    
    
    
        
    
}
