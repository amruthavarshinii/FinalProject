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
import javax.swing.JOptionPane;
import smartTravelApp.controller.utils.algorithms.nearestNeighbor.NearestNeighbor;

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
    
    /**
     * Adds a location to the current selection
     * @param longitude The longitude of the location
     * @param latitude The latitude of the location
     */
    public void addLocation(String longitude, String latitude) 
    {
        locations.addLocation(longitude, latitude);
        System.out.println("Location added");
    }
    
    /**
     * Prints all location added to the selection
     */
    public void printLocations()
    {
        Iterator e = locations.iterator();
        while (e.hasNext())
        {
            Location location = (Location) e.next();
            System.out.println(location);
        }
    }
    
    /**
     * Deletes the current selection
     */
    public void clearSelection()
    {
        locations = new LocationsContainer();
        System.out.println("Selection cleared");
    }
    
    /**
     * Process the current selection of locations
     */
    public void processSelection()
    {
        String message;
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
            long[][] distances = request.getDistanceMatrix(places);
            //printSymmetricMatrix(distances);
            message = processUsingNearestNeighbor(distances, 0);
            JOptionPane.showMessageDialog(null, message, "Result", 1);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No locations selected!", "Error", 0);
        }
    }
    
    private String processUsingNearestNeighbor(long[][] distances, int startingNode)
    {
        NearestNeighbor test = new NearestNeighbor();           
        Integer[] solution = test.processTour(distances, startingNode);
        String message = "Path:\n";
        for(int i : solution)
        {
            message = message.concat(i+"\n");
        }
        return message;
    }
   
    private void printSymmetricMatrix(long[][] distances)
    {
        for (int i = 0; i < distances.length; i++)
            {
                for (int j = 0; j < distances.length; j++)
                {
                    System.out.print(distances[i][j] + "  ");
                }
                System.out.println("");
            }
    }
}
