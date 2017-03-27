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
     * A method that takes in a list of locations as part of a tour 
     * and returns an optimal distance-effective path.
     * @param locations Locations to be processed
     * @param startingNode Starting node for the tour
     * @return
     */
    public final String processInstance(LocationsContainer locations, int startingNode)
    {       
        Integer[] solution = processTour(locations, startingNode);
        return formatMessage(solution, locations);
    }
    
    final String formatMessage(Integer[] solution, LocationsContainer locations){
        String message = "Path:\n";
        for (int i = 0; i < solution.length; i++)
        {
            message = message.concat(i + "- " +locations.getLocation(solution[i]).getPlaceDescription() +"\n");
        }
        return message;
    }
    
    protected abstract Integer[] processTour(LocationsContainer locations, int start);
}
