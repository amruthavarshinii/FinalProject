package smartTravelApp.controller.utils.algorithms.nearestNeighbor;

import smartTravelApp.controller.utils.algorithms.TSPAlgorithm;
import java.util.HashMap;
import smartTravelApp.controller.utils.GoogleMapsDistanceMatrixClient;
import smartTravelApp.model.LocationsContainer;

/*
 * Written by Rafael Lopez <lopez.rafael08@gmail.com>, 2016
 */

public class NearestNeighbor extends TSPAlgorithm
{    
    @Override
    public Integer[] processTour(LocationsContainer locations, int start)
    {
        GoogleMapsDistanceMatrixClient request = new GoogleMapsDistanceMatrixClient(); 
        long[][] distances = request.getDistanceMatrix(locations);
        
        Integer[] solution = new Integer[distances.length];
        long distance = 0;
        int currentNode = 0;
        
        for (int i = 1; i < distances.length; i++)
        {    
            if (i == 1)
            {
                solution[0] = start;
                currentNode = start;
            }
            
            HashMap results = findNearestNeighbor(distances, currentNode, solution);
            distance += (Long)results.get("distance");
            currentNode = (Integer)results.get("index");
            solution[i] = currentNode;        
        }
        System.out.println("*****Neareast Neighbor Algorithm******");
        System.out.println("Total distance: " + distance);
        return solution;
    }
    
    private HashMap findNearestNeighbor(long[][] data, int currentNode, Integer[] nodesVisited)
    {
        int indexMin=0;
        long currentValue;
        long tempMin = 0;
        for (int i = 0; i < data.length; i++)
        {
            if ( currentNode != i )
            {
                if( isValidNode(nodesVisited, i) )
                {
                    currentValue = data[currentNode][i];
                    if (tempMin != 0)
                    {
                        if ( currentValue < tempMin )
                        {
                            tempMin = currentValue;
                            indexMin = i;
                        }
                    }
                    else
                    {
                        tempMin = currentValue;
                        indexMin = i;
                    }
                }
            }
        }
        HashMap result = new HashMap();
        result.put("distance", tempMin);
        result.put("index", indexMin);
        
        return result;
    }
    
    private boolean isValidNode(Integer[] visitedNodes, int index)
    {
        boolean alreadyVisited = true;
        for (Integer i : visitedNodes)
        {
            if(i != null && i == index)
            {
                alreadyVisited = false;
                break;
            }
        }
        return alreadyVisited;
    }
}