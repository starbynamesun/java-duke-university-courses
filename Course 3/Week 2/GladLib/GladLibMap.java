
/**
 * Write a description of class GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;

public class GladLibMap {

    private HashMap <String, ArrayList<String>> myMap;
    private ArrayList<String> wordsUsed;
    private ArrayList<String> usedCategories;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        initializeFromSource(dataSourceDirectory);
        myMap = new HashMap<>();
        wordsUsed = new ArrayList<String>();
        usedCategories = new ArrayList<String>();
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myMap = new HashMap<>();
        wordsUsed = new ArrayList<String>();
        usedCategories = new ArrayList<String>();
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        
        String[] categories = {"adjective", "noun", "color", "country", "name", 
            "animal", "time", "verb", "fruit"};
        
        for(String category : categories) {
        
            ArrayList<String> list = readIt(source+ "/" + category + ".txt");
            myMap.put(category, list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {

        if(!usedCategories.contains(label)) {
            usedCategories.add(label);
        }
        
        if (label.equals("number")){
            return "" + myRandom.nextInt(50)+5;
        }

        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        
        while(true) {
            if(!wordsUsed.contains(sub)) {
                wordsUsed.add(sub);
                break;
            } else {
                sub = getSubstitute(w.substring(first+1,last));
            }
        }   
        
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w + " ");
            charsWritten += w.length() + 1;
        }
        System.out.println(" \nTotal number of words that were replaced: " + wordsUsed.size());
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsInMap() {
    
        int totalNumber = 0;
        
        for(String word : myMap.keySet()) {
            totalNumber += myMap.get(word).size();
        }
        
        return totalNumber;
    }
    
    private int totalWordsConsidered() {
    
        int consideredWords = 0;
        
        for(int i = 0; i < usedCategories.size(); i++){
            consideredWords += myMap.get(usedCategories.get(i)).size();             
        }
    
        return consideredWords;
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate.txt");
        printOut(story, 60);
        System.out.println("Total words in map: " + totalWordsInMap());
        System.out.println("Total words considered: " + totalWordsConsidered());
        wordsUsed.clear();
        usedCategories.clear();
    }

    
}
