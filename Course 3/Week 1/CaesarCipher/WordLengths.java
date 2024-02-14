
/**
 * Write a description of class WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
import java.util.Arrays;

public class WordLengths {

    public void countWordLengths(FileResource resource, int[] counts) {
        
        for(String word : resource.words ()){
            //check if the first character is non-letter
            if (!Character.isLetter(word.charAt(0))){
                word = word.substring(1, word.length());
            }
            //check if ladt character is non-letter
            if (!Character.isLetter(word.charAt(word.length() - 1))){
                word = word.substring(0, word.length() - 1);
                //length--
            }

            int length = word.length();
            //System.out.println(length + " " + word);
            counts[length] += 1;
        }
        
        System.out.println(Arrays.toString(counts));
        System.out.println("index of largest element: " 
                            + indexOfMax(counts));
    }
    
    int indexOfMax(int[] values){
        int index = 0;
        
        for(int i = 0; i < values.length; i++){
            if(values[i] > values[index]){
                index = i;
            }
        }
        
        return index;
    }
    
    int countWords(FileResource fr){
        int longest = 0;
        
        for(String word : fr.words()){
            if(word.length() > longest){
                longest = word.length();
            }
        }
        
        return longest + 1;
    }
    
    void testCountWordLengths() {
        String[] sample = {"romeo.txt"};
        
        for(int i = 0; i < sample.length; i++){
            FileResource fr = new FileResource();
            String words = fr.asString();
            int[] counts = new int[countWords(fr)];
            System.out.println("done with " + sample[i]);
            System.out.println(counts.length);
            countWordLengths(fr, counts);
        }
    
    }
}
    
    //This method should read in the words from resource 
    //and count the number of words of each length for 
    //all the words in resource, 
    //storing these counts in the array counts.


