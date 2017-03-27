/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartTravelApp.controller.utils.algorithms.geneticAlgorithm;

import smartTravelApp.controller.utils.algorithms.TSPAlgorithm;
import smartTravelApp.model.LocationsContainer;

/**
 *
 * @author Rafael.Lopez
 */
public class GeneticAlgorithm extends TSPAlgorithm
{
    @Override
    protected Integer[] processTour(LocationsContainer locations, int start) {
        Population population = generateInitialPopulation(locations);
        
        for (Tour tour : population.getPopulation())
        {
            System.out.println(tour.getFitness());
        }
        
        population.deletePopulation();
        return null;
    }  
    
    private Population generateInitialPopulation(LocationsContainer locations)
    {
        //As specified in the project documentation, the size of the initial
        //population can be any (pag. 10).
        int populationSize = locations.size() * 2;
        
        Population population = new Population();
        population.initializePopulation(locations, populationSize);
        return population;
    }
}
