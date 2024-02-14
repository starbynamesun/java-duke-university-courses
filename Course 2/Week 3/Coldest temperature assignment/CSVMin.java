/**
 * Find the highest (hottest) temperature in any number of files of CSV weather data chosen by the user.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMin {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord minSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            if (!currentRow.get("TemperatureF").equals("-9999")){
                minSoFar = getMinOfTwo(currentRow, minSoFar);
            }
        }
        //The largestSoFar is the answer
        return minSoFar;
    }

    public void testColdestInDay () {
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + coldest.get("TemperatureF") +
                   " at " + coldest.get("TimeEST"));
    }

    public CSVRecord coldestInManyDays() {
        CSVRecord minSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            // use method to compare two records
            minSoFar = getMinOfTwo(currentRow, minSoFar);
        }
        //The largestSoFar is the answer
        return minSoFar;
    }

    public CSVRecord getMinOfTwo (CSVRecord currentRow, CSVRecord minSoFar) {
        //If largestSoFar is nothing
        if (minSoFar == null) {
            minSoFar = currentRow;
        }
        //Otherwise
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double minTemp = Double.parseDouble(minSoFar.get("TemperatureF"));
            //Check if currentRow’s temperature > largestSoFar’s
            if (currentTemp < minTemp) {
                //If so update largestSoFar to currentRow
                minSoFar = currentRow;
            }
        }
        return minSoFar;
    }

    
    //??????????????????????????????
    public void testColdestInManyDays () {
        CSVRecord coldest = coldestInManyDays();
        System.out.println("Coldest temperature was " + coldest.get("TemperatureF") +
                   " at " + coldest.get("DateUTC"));  
    }
    
    public String fileWithColdestTemperature () {
        File fileWithColdest = null;
        CSVRecord minSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            // use method to compare two records
            minSoFar = getMinOfTwo(currentRow, minSoFar);
            if (minSoFar == currentRow){
                fileWithColdest = f;
            }
        }
        //The largestSoFar is the answer
        return fileWithColdest.getName();
    }
    
    public void testFileWithColdestTemperature () {
        String coldest = fileWithColdestTemperature();
        
        System.out.println("Coldest day was in file" + coldest);
        //also call for coldestHour method
    
    }
    
    public CSVRecord lowestHumidityInFile (CSVParser parser){
        CSVRecord lowestSoFar = null;
        //For each row (currentRow) in the CSV Fill
            
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            lowestSoFar = getMinOfTwoHumid(currentRow, lowestSoFar);
        }
        return lowestSoFar;
    }
    
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowest = lowestHumidityInFile(parser);
        System.out.println("Lowest humidity was " + lowest.get("Humidity") +
                   " at " + lowest.get("DateUTC"));
                   
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            // use method to compare two records
            lowestSoFar = getMinOfTwoHumid(currentRow, lowestSoFar);
        }
        //The largestSoFar is the answer
        return lowestSoFar;
    }
    
    
    public CSVRecord getMinOfTwoHumid (CSVRecord currentRow, CSVRecord lowestSoFar) {
        //If largestSoFar is nothing
        if (lowestSoFar == null) {
            lowestSoFar = currentRow;
            }
            //Otherwise
        else {
            if (!currentRow.get("Humidity").equals("N/A")){
                int currentHumid = Integer.parseInt(currentRow.get("Humidity"));
                int minHumid = Integer.parseInt(lowestSoFar.get("Humidity"));
                //Check if currentRow’s temperature > largestSoFar’s
                if (currentHumid < minHumid) {
                    //If so update largestSoFar to currentRow
                    lowestSoFar = currentRow;
                }
            }
        }
        return lowestSoFar;
    }
    
    public void  testLowestHumidityInManyFiles() {
    CSVRecord lowest = lowestHumidityInManyFiles();
    System.out.println("Lowest humidity was " + lowest.get("Humidity") +
                   " at " + lowest.get("DateUTC"));
    }
    

    public double averageTemperatureInFile(CSVParser parser){
        
        double sum = 0.0;
        int count = 0;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            if (sum == 0) {
                sum = Double.parseDouble(currentRow.get("TemperatureF"));
                count ++;
            }
            //Otherwise
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                sum += currentTemp;
                count++;
            }
        }
        
        if (count > 0) {
        return sum / count;
        } else {
            return 0.0;
        }
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        double average = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file was " + 
                            average);
    }
    
    public double averageTemperatureWithHighHumidityInFile(
                                                        CSVParser parser, 
                                                        int value){
    //returns a double that represents the average temperature of 
    //only those temperatures when the humidity was greater than or 
    //equal to value.
        double sum = 0.0;
        int count = 0;
        
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            int humidity = Integer.parseInt(currentRow.get("Humidity"));
                if (sum == 0 && humidity >= value) {
                sum = Double.parseDouble(currentRow.get("TemperatureF"));
                count ++;
            } else {
                //Otherwise
                if (humidity >= value){
                    double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                    sum += currentTemp;
                    count++;
                }   
            }
        }
        
        if (count > 0) {
            return sum / count;
        } else {
            return 0.0;
        }                                         
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        double average = averageTemperatureWithHighHumidityInFile
                        (fr.getCSVParser(),80);
        System.out.println("Average temperature when Humidity more than 80 was " + 
                            average);
    }
}
