/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartTravelApp.model;

/**
 *
 * @author Rafael.Lopez
 */
public class Location 
{
    private int locationId;
    private double longitude;
    private double latitude;
    
    public Location(String longitude, String latitude, int locationId)
    {
        this.longitude = Double.parseDouble(longitude.trim());
        this.latitude = Double.parseDouble(latitude.trim());
        this.locationId = locationId;
    }
    
    @Override
    public String toString()
    {
        return "ID: " + locationId + ", Long: " + longitude + ", Lat: " + latitude;
    }
}
