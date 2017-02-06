/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtest;

/**
 *
 * @author Rafael.Lopez
 */
public class Location 
{
    private double longitude;
    private double latitude;
    
    public Location(String longitude, String latitude)
    {
        this.longitude = Double.parseDouble(longitude.trim());
        this.latitude = Double.parseDouble(latitude.trim());
    }
    
    @Override
    public String toString()
    {
        return "Long: " + longitude + ", " + "Lat: " + latitude;
    }
}
