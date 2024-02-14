
/**
 * Write a description of class WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.util.*;


public class WordFrequencies {
    
    private ArrayList<String> myWords; 
    private ArrayList<Integer> myFreqs;
    
    //The kth position in myFreqs should represent the number of 
    //times the kth word in myWords occurs in the file. 
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();
        for(String s : resource.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            
            if (index == -1){
                myWords.add(s);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value+1);
            }
        }
    }
    
    
    private int findIndexOfMax() {
        int max = 0;
        
        for (int i = 1; i < myFreqs.size(); i++){
            if(myFreqs.get(i) > myFreqs.get(max)) {
                max = i;
            }
        }
        return max;
    }
    
    public void tester(){
        findUnique();
        System.out.println("# unique words: " + myWords.size());
        
        for(int i = 0; i < myWords.size(); i++){
            System.out.println(myFreqs.get(i) + "\t" + myWords.get(i));
        }  
        //System.out.println(myWords.get(findIndexOfMax()) + " with occurence " + findIndexOfMax() + " is most often");
        
        int maxIndex = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " + 
                            myWords.get(maxIndex) + " " + myFreqs.get(maxIndex));
    }
}
