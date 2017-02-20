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
public class LocationsContainer extends ArrayList
{   
    public void addLocation(String latitude, String longitud)
    {
        Location location = new Location(latitude, longitud);
        this.add(location);
    }
}
