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
    private final int locationId;
    private final double longitude;
    private final double latitude;

    /**
     * Returns the location ID
     * @return
     */
    public int getLocationId() {
        return locationId;
    }

    /**
     * Returns the longitude of this location
     * @return
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Returns the latitude of this location
     * @return
     */
    public double getLatitude() {
        return latitude;
    }
    
    /**
     * Creates a Location object by taking in the longitude, latitude and locationID
     * @param longitude Longitude of the location
     * @param latitude Latitude of the location
     * @param locationId Location ID
     */
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
