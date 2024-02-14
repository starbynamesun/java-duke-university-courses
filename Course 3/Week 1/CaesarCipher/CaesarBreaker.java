
/**
 * Write a description of class CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
import java.util.Arrays;

public class CaesarBreaker {
    public String decrypt(String encrypted){
        
        CaesarCipher cc = new CaesarCipher();
        int[] fregs = countLetters(encrypted);
        int maxDex = maxIndex(fregs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4-maxDex) ;
        }
        
        System.out.println("Assumming most common letter is 'e' the key is " + dkey);
        return cc.encrypt (encrypted, 26-dkey);
    }
    
    public int maxIndex (int[] vals) {
        int maxDex = 0;
        
        for (int i = 0; i < vals.length; i++) {
            if (vals [i] > vals [maxDex]) {
                maxDex = i;
            }
        }
        
        return maxDex;
    }
    
    public int[] countLetters (String encrypt){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        
        for (int i = 0; i < encrypt.length(); i++){
            char ch = Character.toLowerCase(encrypt.charAt(i));
            int dex = alph.indexOf(ch);
            if (dex != -1){
                counts [dex] += 1;
            }
        }
        
        return counts;
    }
    
    public String halfOfString(String message, int start){
    
        StringBuilder halfString = new StringBuilder();
        
        for (int i = start; i < message.length(); i+=2) {
             halfString.append(message.charAt(i));
        }
        
        return halfString.toString();
    }
    
    public int getKey(String s) {
        int[] freqs = countLetters(s);
        int key = maxIndex(freqs) - 4;
        if (maxIndex(freqs) < 4) {
            key = 26 - (4-maxIndex(freqs)) ;
        }
        
        return key;
    } 
    
    public void decryptTwoKeys(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        String half1 = halfOfString(encrypted, 0);
        String half2 = halfOfString(encrypted, 1);
        
        int key1 = getKey(half1);
        int key2 = getKey(half2);
        
        System.out.println("Key 1 is: " + key1 + " Key 2 is: " + key2);
        System.out.println(cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2));
    }
    
    public void decryptTwoKeysKnown(String encrypted, int key1, int key2) {
        //if keys are known
        CaesarCipher cc = new CaesarCipher();
        System.out.println(cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2));
    }
    
    public void testDecrypt() {
        FileResource fr = new FileResource ();
        System.out.println(decrypt(fr.asString()));
        //String input = "FIRST LEGION ATTACK EAST FLANK!";
    
    }
    
    public void testHalfOfString() {
    
        String message = "Qbkm Zgis";
        int start = 1;
        String half = halfOfString(message, start);
        
        System.out.println("Half string: " + half);
    }
    
    public void testDecryptTwoKeys() {
        FileResource fr = new FileResource ();
        String message = fr.asString();
        decryptTwoKeys(message);
    }
}