/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartTravelApp.controller;

import smartTravelApp.controller.utils.GoogleMapsDistanceMatrixClient;
import smartTravelApp.model.LocationsContainer;
import smartTravelApp.model.Location;
import java.util.Iterator;

/**
 *
 * @author Rafael.Lopez
 */
public class JavaScriptHandler 
{
    LocationsContainer locations;
    
    public JavaScriptHandler()
    {
        clearSelection();
    }
    
    public void addLocation(String longitude, String latitude) 
    {
        locations.addLocation(longitude, latitude);
        System.out.println("Location added");
    }
    
    public void printLocations()
    {
        Iterator e = locations.iterator();
        while (e.hasNext())
        {
            Location location = (Location) e.next();
            System.out.println(location);
        }
    }
    
    public void clearSelection()
    {
        locations = new LocationsContainer();
        System.out.println("Selection cleared");
    }
    
    public void processSelection()
    {
        if (!locations.isEmpty())
        {
            String places[] = new String[locations.size()];

            for (int index = 0; index < locations.size(); index++)
            {
                Location location = (Location) locations.get(index);
                String coordinates = "" +location.getLatitude() + "," + location.getLongitude();
                places[index] = coordinates;
            }

            GoogleMapsDistanceMatrixClient request = new GoogleMapsDistanceMatrixClient();  
            request.getDistanceMatrix(places);
        }
        else
        {
            System.out.println("No locations selected");
        }
    }
}
