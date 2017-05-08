/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartTravelApp.model.factories;

import smartTravelApp.model.algorithms.TSPAlgorithm;
import smartTravelApp.model.algorithms.bruteForce.BruteForce;
import smartTravelApp.model.algorithms.geneticAlgorithm.GeneticAlgorithm;
import smartTravelApp.model.algorithms.nearestNeighbor.NearestNeighbor;

/**
 *
 * @author Rafael.Lopez
 */
public class AlgorithmFactory 
{
    public static TSPAlgorithm createAlgorithmInstance(int numberOfLocations)
    {
        TSPAlgorithm algorithm = null;
        
        if (numberOfLocations < 5)
        {
            algorithm = new BruteForce();
        }
        else if (numberOfLocations < 8)
        {
            algorithm = new NearestNeighbor();
        }
        else
        {
            algorithm = new GeneticAlgorithm();
        }
                
        return algorithm; 
    }
}
