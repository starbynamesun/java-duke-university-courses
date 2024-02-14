/**
 * Print out the names for which 100 or fewer babies were born in a chosen CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                               " Gender " + rec.get(1) +
                               " Num Born " + rec.get(2));
            }
        }
    }
    
    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int countNames = 0;
        int countBoysNames = 0;
        int countGirlsNames = 0;
        
        for(CSVRecord rec : fr.getCSVParser (false)) {
            int numBorn = Integer.parseInt(rec.get (2));
            totalBirths += numBorn;
            
            if (rec.get(1).equals ("M")) {
                totalBoys += numBorn;
                countBoysNames++;
                countNames++;
            } else {
                totalGirls += numBorn;
                countGirlsNames++;
                countNames++;
            }   
        }
        
        System.out.println("total births = " + totalBirths);
        System.out.println("Boys names = " + countBoysNames);
        System.out.println("Girls names = " + countGirlsNames);
        System.out.println("Total names = " + countNames);
    }
    
    public void testTotalBirths () {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public int getRank (int year, String name, String gender){
    
        int rank = 0;
        //FileResource fr = new FileResource();
        String filename = "data/yob" + year + ".csv";
        FileResource fr = new FileResource(filename);
        
        for(CSVRecord rec : fr.getCSVParser (false)) {
            String currentName = rec.get(0);
            String currentGender = rec.get(1);
            int numBirths = Integer.parseInt(rec.get(2));
            //search name, gender, if not in file -1;
            //row1=rank1
            //row2=rank2
            //row lastgirl = rownumber
            //row firstboy = rownumber Last girl +1
            
            if (currentGender.equals(gender)) {
                rank++; // Incremen
                if (currentName.equals(name)) {
                    return rank; // Return the rank if the name is found
                }
            }
        }
        
        return rank;
    }
    
    public void testGetRank (){
        int year = 1971;
        String name = "Frank";
        String gender = "M";
        
        int nameRank = getRank(year,name,gender);
        System.out.println("Name " + name + " rank is " + nameRank);
    }
    
    public String getName (int year, int rank, String gender){
        
        FileResource fr = new FileResource();
        int currentRank = 0;
        
        for(CSVRecord rec : fr.getCSVParser (false)) {
            String currentGender = rec.get(1);
            
            if (currentGender.equals(gender)) {
                currentRank++; // Incremen
                if (currentRank == rank) {
                    return rec.get(0);
                }
            }
        }
        
        return "No name";
    }
    
    public void testGetName (){
        int year = 1982;
        int rank = 450;
        String gender = "M";
        //rank 350 in 1980?
        //boy 450 in 1982
        
        String nameOfThisRank = getName (year,rank,gender);
        System.out.println("Name associated with this rank " + nameOfThisRank);
    }
    
    public void whatIsNameInYear(
    String name,
    int year,
    int newYear,
    String gender){
        
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord rec = getRecord(year,name,gender,fr);
            if (rec != null){
                int currentRank = getRank(year,name,gender);
                String nameInOtherYear = getName(newYear,currentRank,gender);
                System.out.println(name + " born in " + year + 
                                " would be " +  nameInOtherYear + 
                                " if born in " + newYear);
                return;
            }
        }
            System.out.println(name + " not found in any file for the year " + year);
    }
        
    public CSVRecord getRecord (int year, String name, String gender, FileResource fr){
        
        for (CSVRecord rec : fr.getCSVParser (false)) {
            // use method to get largest in file.
            if (rec.get(0).equals(name) && rec.get(1).equals(gender)){
                return rec;
            }
        }
        return null;
    }
    
    public void testWhatIsNameInYear (){
        
        String name = "Owen";
        int year = 1974;
        int newYear = 2014;  
        String gender = "M";
        
        whatIsNameInYear(name, year,newYear,gender);
    
        
        //Suppose Susan was born in 1972. 
        //Based on her name's rank in 1972, what would her name be if she were born in 2014
        //Owen was born in 1974. Based on his name's rank in 1974, 
        //what would his name be if he were born in 2014
    }
    
    public int yearOfHighestRank(String name, String gender) {
        
        int previousRank = 0; 
        int year = -1;
        int currentYear = 0;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            
            //FileResource fr = new FileResource(f);
            //CSVRecord rec = getRecord(year, name, gender, fr);
            
            //if (rec != null) {
            String fileName = f.getName();
            String getYear = fileName.substring(fileName.indexOf("yob") + 3, fileName.indexOf(".csv"));
            currentYear = Integer.parseInt(getYear);
                
            int currentRank = getRank(currentYear,name,gender);
            
            if ((currentRank != -1) && (previousRank == 0 || currentRank < previousRank)){
                 previousRank = currentRank;
                 year = currentYear;
            }
            //}
        }

        return year;
    }
    
    public void testYearOfHighestRank() {
        String name = "Mich";
        String gender = "M";

        int highestYear = yearOfHighestRank(name, gender);
        System.out.println("Year with the highest rank for " 
            + name + " and gender " + gender + ": " + highestYear);
            
        //Genevieve
        //Mich
    
    }
    
    public double getAverageRank(String name, String gender){
        
        double averageRank = -1;
        double count = 0.0;
        int rankSum = 0;
        
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
        
            count++;
            String fileName = f.getName();
            String getYear = fileName.substring(fileName.indexOf("yob") + 3, fileName.indexOf(".csv"));
            int currentYear = Integer.parseInt(getYear);
            int currentRank = getRank(currentYear,name,gender);
            rankSum+=currentRank;
        }
        averageRank = rankSum / count;
        return averageRank;
    }
    
    public void testAverageRank(){
        String name = "Robert";
        String gender = "M";
        
        double average = getAverageRank(name, gender);
        System.out.println("Average rank for " 
                + name + " gender " + gender + ": " + average);
        //Susan
        //Robert
    }
   
    
    public int getTotalBirthsRankedHigher (int year, String name, String gender){
        
        int totalBirthsRankedHigher = 0;
        int boysCount = 0;
        int girlsCount = 0;
        int rank = getRank(year, name, gender);
        FileResource fr = new FileResource();
        
        for(CSVRecord rec : fr.getCSVParser (false)) {
            
            //String currentGender = rec.get(1);
            //String currentName = rec.get(0);
            
            int numBorn = Integer.parseInt(rec.get(2));
            
        if (rec.get(1).equals("M") && gender.equals("M")){
            boysCount++;
            if (boysCount < rank){
                totalBirthsRankedHigher += numBorn;
                
            }
        }
            
        if (rec.get(1).equals("F") && gender.equals ("F")) {
            girlsCount++;
                
            if (girlsCount < rank){
                totalBirthsRankedHigher += numBorn;
            }
        }
        }
        
        return totalBirthsRankedHigher;
    }   
    
    
    public void testGetTotalBirthsRankedHigher() {
        
        int year = 1990;
        String name = "Emily";
        String gender = "F";
        
        int birthRankedHigher = getTotalBirthsRankedHigher(year, name, gender);
        System.out.println("Total births of names ranked higher than " + name + ": " + birthRankedHigher);
        
        //total number of boys born in 1990 with names ranked higher than the boy's name 
        //"Drew" in 1990?
        //1990
        //Emily" in 1990?
    }
}



