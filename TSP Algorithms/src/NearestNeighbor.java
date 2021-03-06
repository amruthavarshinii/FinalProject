
import java.util.HashMap;

/*
 * Written by Rafael Lopez <lopez.rafael08@gmail.com>, 2016
 */

public class NearestNeighbor 
{
    private static int[][] createSampleData()
    {
        /* Given a TSP instance with 4 cities
           Represent TSP as an undirected weighted graph where:
           Cities = vertices
           Paths = edges
           Distances = edge's weight
        
           Transform TSP into a symmetric matrix to process it
           
           City  0  1  2  3
                 __________
            0 |  0  7  8 10
            1 |  7  0  5  4
            2 |  8  5  0  6
            3 | 10  4  5  0
        */
        int [][] data = new int[4][4];
        data[0][0] = 0;
        data[0][1] = 7;
        data[0][2] = 8;
        data[0][3] = 10;
        data[1][0] = 7;
        data[1][1] = 0;
        data[1][2] = 5;
        data[1][3] = 4;
        data[2][0] = 8;
        data[2][1] = 5;
        data[2][2] = 0;
        data[2][3] = 6;
        data[3][0] = 10;
        data[3][1] = 4;
        data[3][2] = 6;
        data[3][3] = 0;
        return data;
    }
    
    public static Integer[] findSolution(int[][] data, int start)
    {
        Integer[] solution = new Integer[data.length];
        int distance = 0;
        int currentNode = 0;
        
        for (int i = 1; i < data.length; i++)
        {    
            if (i == 1)
            {
                solution[0] = start;
                currentNode = start;
            }
            
            HashMap results = findNearestNeighbor(data, currentNode, solution);
            distance += (Integer)results.get("distance");
            currentNode = (Integer)results.get("index");
            solution[i] = currentNode;        
        }
        System.out.println("*****Neareast Neighbor Algorithm******");
        System.out.println("Total distance: " + distance);
        return solution;
    }
    
    private static HashMap findNearestNeighbor(int[][] data, int currentNode, Integer[] nodesVisited)
    {
        int indexMin=0;
        int currentValue;
        int tempMin = 0;
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
    
    private static boolean isValidNode(Integer[] visitedNodes, int index){
        boolean alreadyVisited = true;
        for (Integer i : visitedNodes)
        {
            if(i != null && i == index){
                alreadyVisited = false;
                break;
            }
        }
        return alreadyVisited;
    }
    
    public static void main(String[] args)
    {
        Integer[] solution = findSolution(FileHelper.getDataFromFile("4locations.txt", 4), 0);
        System.out.println("Path: ");
        for(int i : solution)
        {
            System.out.println(i);
        }
    }
}
