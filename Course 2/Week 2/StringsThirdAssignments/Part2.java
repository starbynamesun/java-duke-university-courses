import edu.duke.*;
import java.io.*;
/**
 * Write a description of class Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    
    public int howMany(String a, String b) {
        
        int count = 0;
        int startIndex = b.indexOf(a);
        
        while (startIndex != -1) {
            int currIndex = b.indexOf(a, startIndex+1);
            if (currIndex == -1){
                break;
            }
            count++;
            startIndex = currIndex;
        }
          
         if (startIndex != -1) {
        count++;
        }
        return count;
    }
    //return 
    //int how many times string a appears in string b
    //each occurrence of stringa must not overlap with another occurrence of it

    public void testHowMany() {
        //String a = "AA";
        //String b = "ATAAAA";
        String a = "GAA";
        String b = "GAAGAAGAAGAAGAA";
        int occurence = howMany(a,b);
        System.out.println("There are " + occurence + 
        " strings in the main string");
        System.out.println("tests finished");
    }
    
    }
