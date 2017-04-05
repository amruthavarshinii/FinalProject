/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartTravelApp.model.algorithms.geneticAlgorithm;

import smartTravelApp.controller.utils.GoogleMapsDistanceMatrixClient;
import smartTravelApp.model.Location;
import smartTravelApp.model.LocationsContainer;

/**
 *
 * @author Rafael.Lopez
 */
public class Tour 
{
    private LocationsContainer locations;
    private long fitnessValue;
    
    public Tour(LocationsContainer locations)
    {
        this.locations = locations;
    }
    
    public Tour()
    {
        locations = new LocationsContainer();
    }
    
    /**
     * Gets the tour's fitness
     * @return
     */
    public long getFitness()
    {
        if (fitnessValue == 0)
        {
            GoogleMapsDistanceMatrixClient client = new GoogleMapsDistanceMatrixClient();

            //To be started in position 1 as two locations are needed
            for (int i = 1; i < locations.size(); i++)
            {
                Location origin = locations.get(i - 1);
                Location destination = locations.get(i);

                fitnessValue += client.getDistanceBetweenTwoLocations(origin, destination);
            }
        }
        
        return fitnessValue;
    }
    
    public LocationsContainer getLocations()
    {
        return locations;
    }
    
    public Location getLocation(int index)
    {
        return locations.get(index);
    }
    
    public int getNumberOfLocations()
    {
        return locations.size();
    }
    
    public void addLocation(Location location)
    {
        locations.add(location);
    }
    
    public void setLocationAt(int index, Location location)
    {
        locations.set(index, location);
    }
}
