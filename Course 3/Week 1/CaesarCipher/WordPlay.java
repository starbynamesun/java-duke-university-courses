
/**
 * Write a description of class WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class WordPlay {

    public boolean isVowel(char ch){
        
        ch = Character.toLowerCase(ch);
        
        if (ch == 'a' ||
            ch == 'e' ||
            ch == 'i' ||
            ch == 'o' ||
            ch == 'u') {
            
            return true;
        }
        
        return false;
    }
        
    public void testIsVowel(){
        char ch = 'j';
        
        System.out.println("It's a vowel " + isVowel(ch));
        
    }
    
    public String replaceVowels(String phrase, char ch){
        
        StringBuilder sb = new StringBuilder(phrase);
        for (int i = 0; i < sb.length(); i++){
            if (isVowel(sb.charAt(i))){
                
                sb.setCharAt(i, ch);

            }
        }
        
        return sb.toString();
    }
    
    public void testReplaceVowels(){
        
        String phrase = "HellO World";
        char ch = '*';
        
        System.out.println("New phrase is " + replaceVowels(phrase,ch));
    
    }
    
    public String emphasize(String phrase, char ch){
        
        StringBuilder sb = new StringBuilder(phrase);
        
        for (int i = 0; i < sb.length(); i++){
            
            // Check if the current character matches the specified character 'ch'
            if (Character.toLowerCase(sb.charAt(i)) == Character.toLowerCase(ch)) {
                // Determine whether the location is odd or even
                if ((i + 1) % 2 == 1) {
                    sb.setCharAt(i,'*');  // Odd location
                } else {
                    sb.setCharAt(i,'+');  // Even location
                }
            }
        }

        return sb.toString();
    }
        
    public void testEmphasize(){
        
        //String phrase = "Mary Bella Abracadabra";
        String phrase = "dna ctgaaactga";
        char ch = 'a';
        
        System.out.println("New phrase is " + emphasize(phrase,ch));
    }
    //For example, the call emphasize(“dna ctgaaactga”, ‘a’) 
    //would return the string “dn* ctg+*+ctg+”, 
    //and the call emphasize(“Mary Bella Abracadabra”, ‘a’) 
    //would return the string “M+ry Bell+ +br*c*d*br+”. 
    //Be sure to test this method. 
}
