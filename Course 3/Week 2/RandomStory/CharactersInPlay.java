
/**
 * Write a description of class CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
import java.util.*;

public class CharactersInPlay {
    
    private ArrayList<String> charactersNames;
    private ArrayList<Integer> countNames;
    
    public CharactersInPlay() {
        charactersNames = new ArrayList<String>();
        countNames = new ArrayList<Integer>();
    }
    
    private void update(String person) {
        int index = charactersNames.indexOf(person);
         
        if(!charactersNames.contains(person)) {
            charactersNames.add(person);
            countNames.add(1);
        } else {
            int value = countNames.get(index);
            countNames.set(index, value+1);
        }
    }
        
    private void findAllCharacters() {
        charactersNames.clear();
        countNames.clear();
        FileResource resource = new FileResource();
        
        for(String s : resource.lines()){
            
            if(s.contains(".")) {
                s = s.trim();
                int index = s.indexOf(".");
                
                if(index != -1){
                    String person = s.substring(0, index);
                    update(person);
                }
            }
        }
    }
    
    private void charactersWithNumParts(int num1, int num2) {
        
        for(int i = 0; i < charactersNames.size(); i++){
            int numParts = countNames.get(i);
            
            if(numParts >= num1 && numParts <= num2) {
                System.out.println(charactersNames.get(i) + " " +  numParts);
            }
        }    
    }
    
    public void tester() {
        findAllCharacters();
        System.out.println("# of records: " + charactersNames.size());
        
        for(int i = 0; i < charactersNames.size(); i++) {
            if(countNames.get(i) > 5)
            System.out.println(countNames.get(i) + "\t" + charactersNames.get(i));
        
        }
    
        charactersWithNumParts(10,15);
    }
}

