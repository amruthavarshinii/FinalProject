/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartTravelApp.model;

import java.util.ArrayList;

/**
 *
 * @author Rafael.Lopez
 */
public class LocationsContainer extends ArrayList<Location>
{   
    private int index;
    
    public LocationsContainer()
    {
        index = 0;
    }
    
    /**
     * Adds a new location to the list
     * @param longitude The longitude of the location
     * @param latitude The latitude of the location
     */
    public void addLocation(String latitude, String longitude, String placeDescription)
    {
        Location location = new Location(latitude, longitude, placeDescription, index);
        this.add(location);
        index++;
    }
    
    public Location getLocation(int index)
    {
        return this.get(index);
    }
}
