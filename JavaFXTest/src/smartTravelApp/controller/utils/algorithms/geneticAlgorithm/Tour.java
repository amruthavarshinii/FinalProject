/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartTravelApp.controller.utils.algorithms.geneticAlgorithm;

import smartTravelApp.controller.utils.GoogleMapsDistanceMatrixClient;
import smartTravelApp.model.Location;
import smartTravelApp.model.LocationsContainer;

/**
 *
 * @author Rafael.Lopez
 */
public class Tour 
{
    LocationsContainer locations;
    
    public Tour(LocationsContainer locations)
    {
        this.locations = locations;
    }
    
    /**
     * Gets the tour's fitness
     * @return
     */
    public long getFitness()
    {
        GoogleMapsDistanceMatrixClient client = new GoogleMapsDistanceMatrixClient();
        long totalDistance = 0;
        
        //To be started in position 1 as two locations are needed
        for (int i = 1; i < locations.size(); i++)
        {
            Location origin = locations.get(i - 1);
            Location destination = locations.get(i);
            
            totalDistance += client.getDistanceBetweenTwoLocations(origin, destination);
        }
        
        return totalDistance;
    }
}
