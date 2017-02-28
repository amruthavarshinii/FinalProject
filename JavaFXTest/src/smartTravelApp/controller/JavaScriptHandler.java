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
        GoogleMapsDistanceMatrixClient request = new GoogleMapsDistanceMatrixClient();
        String [] places = {"52.669720383688166,-8.63525390625", "52.274880130680536,-9.678955078125",
                            "51.91039070988962,-8.4814453125", "53.363665164191865,-6.26220703125"};
        request.getDistanceMatrix(places);
    }
}
