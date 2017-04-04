/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartTravelApp.model.algorithms.geneticAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import smartTravelApp.model.LocationsContainer;

/**
 *
 * @author Rafael.Lopez
 */
public class Population 
{
    ArrayList<Tour> population;
    
    public Population()
    {
        population = new ArrayList<>();
    }
    
    protected void initializePopulation(LocationsContainer locations, int size)
    {
        for (int i = 0; i < size; i++)
        {            
            population.add(generateIndividual(locations));
        }
    }
    
    private Tour generateIndividual(final LocationsContainer locations)
    {
        LocationsContainer locationsToCreateTour = (LocationsContainer) locations.clone();
        Collections.shuffle(locationsToCreateTour);
        return new Tour(locationsToCreateTour);
    }
    
    protected ArrayList<Tour> getPopulation()
    {
        return population;
    }
    
    protected int getPopulationSize()
    {
        return population.size();
    }
    
    protected void deletePopulation()
    {
        population = new ArrayList<>();
    }
    
    protected void addIndividual(Tour tour)
    {
        if (population != null)
        {
            population.add(tour);
        }
    }
    
    protected Tour getFittestIndividual()
    {
        Tour winner = null;
        int firstTour = 0;
        
        if (population != null)
        {
            winner = population.get(firstTour);
            long winnerFitness = winner.getFitness();
            
            for (int i = 1; i < population.size(); i++)
            {         
                Tour tmp = population.get(i);
                long tmpFitness = tmp.getFitness();
                
                if (tmpFitness < winnerFitness)
                {
                    winner = tmp;
                    winnerFitness = tmpFitness;
                }
            }
        }
        return winner;
    }
}
