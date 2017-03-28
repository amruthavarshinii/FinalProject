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
    private int tournamentSize;
    
    @Override
    protected LocationsContainer applyTSPAlgorithm(LocationsContainer locations, int start) 
    {
        //Tournament size is selected randomly and has a direct impact
        //on selection pressure. The selection pressure is the degree to 
        //which the better individuals are favored.
        //http://www.complex-systems.com/pdf/09-3-2.pdf
        tournamentSize = locations.size() / 2;
        int iterations = locations.size();
        
        //Randomly create an initial population
        Population population = generateInitialPopulation(locations);
        
        //Evolve population
        population = evaluatePopulation(population);
        
        //Keep evolving until stop criteria is reached
        for (int i = 0; i < iterations; i++)
        {
            population = evaluatePopulation(population);
        }
 
        return population.getFittestIndividual().getLocations();
    }  
    
    private Population evaluatePopulation(Population population)
    {
        Population newPopulation = new Population();
        
        for (int i = 0; i < population.getPopulation().size(); i++)
        {
            //Selection: Tournament Selection
            Tour parentA = doTournamentSelection(population);
            Tour parentB = doTournamentSelection(population);

            //Crossover
            //A child is generated after applying Crossover using parentA and parentB
            //Add child to the new population
        }
        
        //Mutation
        //Once the new population is generated do Mutation
        
        return newPopulation; 
    }
    
    private Tour doTournamentSelection(Population population)
    {
        Population tournament = new Population();
        
        for (int i = 0; i < tournamentSize; i++)
        {
            int randomTourIndex = (int) (Math.random() * population.getPopulationSize());
            tournament.addIndividual(population.getPopulation().get(randomTourIndex));
        }
        return tournament.getFittestIndividual();
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
