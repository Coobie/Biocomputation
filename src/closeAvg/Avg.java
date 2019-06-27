/*
 * Licensed to JGC
 */
package closeAvg;

import improved.FileRead;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Jacob
 */
public class Avg
{
    public static void main(String[] args)
    {
        FileRead trainData = new FileRead(7);
        int trainCount = 1400; // 70%
        int testCount = 600; // 30%
        ArrayList<double[]> testI = new ArrayList<>();
        ArrayList<Double> testO = new ArrayList<>();
        for (int i = 0; i < 1800; i++)
        {
            Random r = new Random();
            int a = r.nextInt(trainData.getCount());
            double[] tI = trainData.getInput(a);
            double tO = trainData.getOutput(a);
            trainData.getInput().remove(a);
            trainData.getOutput().remove(a);
            //Add test data
            testI.add(tI);
            testO.add(tO);
        }
        
        //Train
        
        //Testing
        ArrayList<Double> distances = new ArrayList<>();
        
                
        //Measure distance
        int total = 0;
        for (int i = 0; i < testI.size(); i++)
        {
            //Measure distance
            
            //Find closest
            
            //check label
        }
        
    }
    
}
