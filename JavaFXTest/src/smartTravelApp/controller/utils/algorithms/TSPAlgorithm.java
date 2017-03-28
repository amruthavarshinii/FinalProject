/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartTravelApp.controller.utils.algorithms;

import smartTravelApp.model.LocationsContainer;

/**
 *
 * @author Rafael.Lopez
 */
public abstract class TSPAlgorithm 
{
    /**
     * A method that takes in a list of locations as part of a tour and returns
     * an optimal distance-effective path produced by a TSP algorithm.
     * @param locations Locations to be processed
     * @param startingNode Starting node for the tour
     * @return
     */
    public final String processInstance(LocationsContainer locations, int startingNode)
    {       
        LocationsContainer solution = applyTSPAlgorithm(locations, startingNode);
        return formatMessage(solution);
    }
    
    final String formatMessage(LocationsContainer solution){
        String message = "Path:\n";
        for (int i = 0; i < solution.size(); i++)
        {
            message = message.concat(i + ") " + solution.get(i).getPlaceDescription() +"\n");
        }
        return message;
    }
    
    /**
     * Abstract method to be implemented using a specific TSP algorithm
     * @param locations Tour to be processed
     * @param start Starting point
     * @return An optimal route produced by a TSP algorithm
     */
    protected abstract LocationsContainer applyTSPAlgorithm(LocationsContainer locations, int start);
}
