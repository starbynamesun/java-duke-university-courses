
/**
 * Write a description of class WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
import java.util.*;

public class WordsInFiles {
    
    private HashMap<String, ArrayList<String>> wordsMap;
    
    public WordsInFiles(){
        wordsMap = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f) {
        
        FileResource fr = new FileResource(f);
        
        for (String word : fr.words()){
            
            ArrayList<String> fileNames;
            
            if(!wordsMap.containsKey(word)) {
                fileNames = new ArrayList<String>();
                fileNames.add(f.getName());
                wordsMap.put(word, fileNames);
            } else {
                fileNames = wordsMap.get(word);
                
                if(!fileNames.contains(f.getName())) {
                    fileNames.add(f.getName());
                    wordsMap.put(word, fileNames);
                }
            }
        }
    }   

    
    private void buildWordFileMap() {
        
        wordsMap.clear();
        DirectoryResource dr = new DirectoryResource();
        
        for(File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    } 
    
    
    private int maxNumber() {
    
        int maxNumber = 0;
        
        for(String word : wordsMap.keySet()) {
            ArrayList<String> fileNames = new ArrayList<String>();
            fileNames = wordsMap.get(word);
            int currentMax = fileNames.size();
            
            if(currentMax > maxNumber) {
                maxNumber = currentMax;
            }
        }
        
        return maxNumber;
    }
    
    private ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> result = new ArrayList<String>();
        
        for(String word : wordsMap.keySet()) {
            if(wordsMap.get(word).size() == number) {
                result.add(word);
            }
        }
        
        return result;
    }
    
    private void printFilesIn(String word) {
        
        for(String key : wordsMap.keySet()) {
            ArrayList<String> fileNames = wordsMap.get(key);
            
            if(word.equals(key)) {
                for(int i = 0; i < fileNames.size(); i++) {
                    System.out.println(fileNames.get(i));
                }
            }
        }
    }
    
    
    public void tester() {
    
        buildWordFileMap();
        System.out.println(wordsMap.size());
        System.out.println("Max number of files any word is in: " + maxNumber());
        
        ArrayList<String> sample = wordsInNumFiles(4);
        System.out.println(sample.size());
        
        printFilesIn("tree");
    }
}  


















