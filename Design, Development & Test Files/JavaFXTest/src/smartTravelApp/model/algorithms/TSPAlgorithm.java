/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartTravelApp.model.algorithms;

import java.util.HashMap;
import smartTravelApp.model.LocationsContainer;

/**
 *
 * @author Rafael.Lopez
 */
public abstract class TSPAlgorithm 
{
    /**
     * A method that takes in a list of locations as part of a tour and starting point.
     * @param locations Locations to be processed
     * @param startingNode Starting node for the tour
     * @return An optimal distance-effective path produced by a TSP algorithm.
     */
    public final String processInstance(LocationsContainer locations, int startingNode)
    {       
        HashMap solution = applyTSPAlgorithm(locations, startingNode);
        LocationsContainer tour = (LocationsContainer)solution.get("tour");
        long distance = (Long)solution.get("distance");
        return formatMessage(tour, distance);
    }
    
    /**
     * A method that takes in a list of locations as part of a tour
     * @param locations Locations to be processed
     * @return An optimal distance-effective path produced by a TSP algorithm.
     */
    public final String processInstance(LocationsContainer locations)
    {       
        return processInstance(locations, 0);
    }
    
    final String formatMessage(LocationsContainer solution, long distance)
    {
        String message = "Path:\n";
        for (int i = 0; i < solution.size(); i++)
        {
            message = message.concat(i + ") " + solution.get(i).getPlaceDescription() +"\n");
        }
        message  += "\nTotal distance travelled: " + (distance/1000) + "km.";
        return message;
    }
    
    /**
     * Abstract method to be implemented using a specific TSP algorithm
     * @param locations Tour to be processed
     * @param start Starting point
     * @return An optimal route produced by a TSP algorithm
     */
    protected abstract HashMap applyTSPAlgorithm(LocationsContainer locations, int start);
}
