/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartTravelApp.controller.utils.algorithms.geneticAlgorithm;

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
    
    protected void deletePopulation()
    {
        population = new ArrayList<>();
    }
}
