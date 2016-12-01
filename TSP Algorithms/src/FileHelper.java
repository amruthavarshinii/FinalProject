
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Written by Rafael Lopez <lopez.rafael08@gmail.com>, 2016
 */

public class FileHelper {

    /* 
        A method that reads a file and return a bidimensional
        array with its content split using white spaces
        as the regex. The file must contain only numbers
        separeted by spaces
    */
    public static int[][] getDataFromFile(String fileName, int numLocations) {
        BufferedReader in = null;
        int [][] data = new int [numLocations][numLocations];

        try {
            in = new BufferedReader(new FileReader(fileName));
            String str;
            int line = 0; 
            while ((str = in.readLine()) != null) {
                String[] lineArray = str.split("\\s+");
                for (int i = 0; i < numLocations; i++) {
                    data[line][i] = Integer.parseInt(lineArray[i]);
                }
                line++;
           }   
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(FileHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return data;
    }
}
