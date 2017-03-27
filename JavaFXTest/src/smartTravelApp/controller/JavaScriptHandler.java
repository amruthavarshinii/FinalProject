/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartTravelApp.controller;

import smartTravelApp.model.LocationsContainer;
import smartTravelApp.model.Location;
import java.util.Iterator;
import javax.swing.JOptionPane;
import smartTravelApp.controller.utils.algorithms.nearestNeighbor.NearestNeighbor;
import smartTravelApp.controller.utils.algorithms.TSPAlgorithm;
import smartTravelApp.controller.utils.algorithms.geneticAlgorithm.GeneticAlgorithm;

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
    public void addLocation(String longitude, String latitude, String placeDescription) 
    {
        locations.addLocation(longitude, latitude, placeDescription);
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
            TSPAlgorithm algorithm = new GeneticAlgorithm();
            message = algorithm.processInstance(locations, 0);
            JOptionPane.showMessageDialog(null, message, "Result", 1);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No locations selected!", "Error", 0);
        }
    }
    
    public void printMessage(String a)
    {
        System.out.println(a);
    }
}
