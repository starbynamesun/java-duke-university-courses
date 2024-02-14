import edu.duke.*;
import java.io.*; 
import org.apache.commons.csv.*;

/**
 * Write a description of class CountryExport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CountryExport {
    
    
    public void tester(){
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    String country = "Nauru";
    String info = countryInfo(parser, country);
    System.out.println(info);
    
    parser = fr.getCSVParser();
    String exportItem1 = "cotton";
    String exportItem2 = "flowers";
    listExportersTwoProducts(parser,exportItem1,exportItem2);
    
    parser = fr.getCSVParser();
    String exportItem = "cocoa";
    int exporters = numberOfExporters (parser, exportItem);
    System.out.println(exporters + " countries exports " + exportItem);
    
    parser = fr.getCSVParser();
    String amount = "$999,999,999,999";
    bigExporters (parser, amount);
    }

    //parser is a CSVParser and country is a String
    
    public String countryInfo (CSVParser parser, String country){
        for (CSVRecord record : parser){
            String countryInformation = record.get("Country");
            
            if (countryInformation.equals(country)){
                String exports = record.get("Exports");
                String exportValue = record.get("Value (dollars)");
                return countryInformation + ": " + exports + ": " + exportValue;
            }
        }
        return "NOT FOUND";
    }

    
    public void listExportersTwoProducts (CSVParser parser, 
                                        String exportItem1, 
                                        String exportItem2){
        boolean foundMatches = false;
        for (CSVRecord record : parser){
            String exports = record.get("Exports");
            
            if (exports.contains(exportItem1) && exports.contains(exportItem2)){
                String countryExporter = record.get("Country");
                //return countryInformation + ": " + exports + ": " + exportValue;
                System.out.println(countryExporter);
                foundMatches = true;
            }
        }
        
        if (!foundMatches){
        System.out.println ("NOT FOUND");
        }
    }   
    
    
    //Write a method named numberOfExporters, 
    //which has two parameters, parser is a CSVParser, 
    //and exportItem is a String. 
    //This method returns the number of countries that export exportItem. 
    //For example, using the file exports_small.csv, 
    //this method called with the item “gold” would return 3.
    
    public int numberOfExporters (CSVParser parser, String exportItem){
    
        boolean foundExporters = false;
        int count = 0;
        
        for (CSVRecord record : parser){
            String exporters = record.get("Exports");
            
            if (exporters.contains(exportItem)){
                String countryExporter = record.get("Country");
                //return countryInformation + ": " + exports + ": " + exportValue;
                //System.out.println(countryExporter);
                count++;
                foundExporters = true;
            }
        }
        
        if (!foundExporters){
        System.out.println ("NOT FOUND");
        }
        
        return count;
    }
    
    
    public void bigExporters (CSVParser parser, String amount){
    
        boolean biggerThanAmount = false;
        for (CSVRecord record : parser){
            String value = record.get("Value (dollars)");
            
            if (value.length() > amount.length()){
                String countryBigValue = record.get("Country");
                String exportValue = record.get("Value (dollars)");
                //return countryInformation + ": " + exports + ": " + exportValue;
                System.out.println(countryBigValue + ": " + exportValue);
                biggerThanAmount = true;
            }
        }
        
        if (!biggerThanAmount){
        System.out.println ("NOT FOUND");
        }
    } 
    //This method prints the names of countries 
    //and their Value amount for all countries 
    //whose Value (dollars) string is longer than the amount string. 
    //You do not need to parse either string value as an integer, 
    //just compare the lengths of the strings. 
    
    
    
    
    //Each time you want to use the parser with another method, 
    //reset the parser by calling fr.getCSVParser() again to get a new parser
    //parser = fr.getCSVParser();
    
    public void readFood() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser){
            System.out.print(record.get("Name") + " ");
            System.out.print(record.get("Favorite Color") + " ");
            System.out.println(record.get("Favorite Food"));
        }
    }
}
