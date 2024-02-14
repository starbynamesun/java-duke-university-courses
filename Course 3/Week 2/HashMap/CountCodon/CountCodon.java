
/**
 * Write a description of class CountCodon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;


public class CountCodon {
    
    private HashMap<String, Integer> codonsMap;
    
    public CountCodon() {
        codonsMap = new HashMap<String, Integer>();
    }
    
    private void buildCodonMap(int start, String dna) {
        
        codonsMap.clear();
        
        for(int i = start; i <= dna.length() - 3; i += 3){
            String codon = dna.substring(i, i+3);
            
            if (codonsMap.keySet().contains(codon)){
                codonsMap.put(codon, codonsMap.get(codon) + 1);
            } else {
                codonsMap.put(codon,1);
            }
            System.out.println(codon + ": " + codonsMap.get(codon));
        }
    }
    
    private String getMostCommonCodon() {
        int maxCount = 0;
        String mostCommonCodon = null;
        
        for(String codon : codonsMap.keySet()) {
            int count = codonsMap.get(codon);
        
            if(count > maxCount) {
                mostCommonCodon = codon;
                maxCount = count;
            }
        }
        
        return mostCommonCodon; 
    }  
    
    private void printCodonCounts(int start, int end) {
        
        for(String codon : codonsMap.keySet()) {
            int codonCounts = codonsMap.get(codon);
            
            if (codonCounts >= start && codonCounts <= end){
                System.out.println(codon + ": " + codonCounts);
            }
        }  
        System.out.println("Unique codons: " + codonsMap.size());
    }
    
    public void tester() {
        
        FileResource resource = new FileResource();
        String dna = resource.asString();
        dna = dna.toUpperCase();
        
        buildCodonMap(0, dna);
        System.out.println(codonsMap.size());
        System.out.println("Most common codon is: " + getMostCommonCodon());
        
        printCodonCounts(1,5);
        
    }
}
























