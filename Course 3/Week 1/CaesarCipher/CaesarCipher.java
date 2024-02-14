
/**
 * Write a description of class CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class CaesarCipher {

    public String encrypt(String input, int key) {
    
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + 
                                    alphabet.substring(0, key);
                                    
        for (int i = 0; i < encrypted.length(); i++) {
            
            //Look at the ith character of encrypted (call it curr
            char currChar = encrypted.charAt(i);
            
            crypting(alphabet,shiftedAlphabet,encrypted,currChar,i);
                //encrypt(“FIRST LEGION ATTACK EAST FLANK!”, 23)
                //should return“CFOPQ IBDFLK XQQXZH BXPQ CIXKH!”
        }
        
        return encrypted.toString();
    }   
    
    
    public String crypting(String alphabet, String shiftedAlphabet, 
                            StringBuilder encrypted, char currChar, int i){
        
        if (Character.isUpperCase(currChar)) {
                //Find the index of currChar in the alphabet (call it
                int idx = alphabet.indexOf(currChar);
                //If currChar is in the alphabet
                if (idx != -1){
                    //Get the idxth character of shiftedAlchabet
                    char newChar = shiftedAlphabet.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            } else if (Character.isLowerCase(currChar)){
                int idx = alphabet.toLowerCase().indexOf(currChar);
                if (idx != -1){
                    //Get the idxth character of shiftedAlchabet
                    char newChar = shiftedAlphabet.charAt(idx);
                    newChar = Character.toLowerCase(newChar);
                    encrypted.setCharAt(i, newChar);
                }
            }
        
        return encrypted.toString();
    }
    
    public void testCaesar() {
        //int key = 23;
        int key = 15;
        //FileResource fr = new FileResource ();
        //String message = fr.asString();
        //String encrypted = encrypt (message, key);
        //String input = "First Legion!";
        //String input = "FIRST LEGION ATTACK EAST FLANK! Eeeeeeeeeeeeeee";
        //String input = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = encrypt (input, key);
        System.out.println("Encrypted message is " + encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println("Decrypted message is " + decrypted);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                                    
        for (int i = 0; i < encrypted.length(); i++) {
            
            //Look at the ith character of encrypted (call it curr
            char currChar = encrypted.charAt(i);
            
            if ((i + 1) % 2 == 1) {
                String shiftedAlphabet = alphabet.substring(key1) + 
                                    alphabet.substring(0, key1);  // Odd location
                crypting(alphabet, shiftedAlphabet, encrypted, currChar, i);
                
            } else {
                String shiftedAlphabet = alphabet.substring(key2) + 
                                    alphabet.substring(0, key2);   // Even location
                crypting(alphabet, shiftedAlphabet, encrypted, currChar, i);
            }
        }
                //encrypt(“FIRST LEGION ATTACK EAST FLANK!”, 23)
                //should return“CFOPQ IBDFLK XQQXZH BXPQ CIXKH!”
        
        return encrypted.toString();
    }   
    
    
    public void testCaesarTwoKeys() {
        //int key1 = 23;
        int key1 = 21;
        //int key2 = 17;
        int key2 = 8;
        //FileResource fr = new FileResource ();
        //String message = fr.asString();
        //String encrypted = encrypt (message, key);
        //String input = "First Legion!";
        //String input = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = encryptTwoKeys (input, key1,key2);
        System.out.println("Encrypted message is " + encrypted);
        String decrypted = encryptTwoKeys(encrypted,26-key1, 26-key2);
        System.out.println("Decrypted message is " + decrypted);
    }
}
    //Parameter key1 is used to encrypt every other character 
    //with the Caesar Cipher algorithm, starting with the first character, 
    //and key2 is used to encrypt every other character, 
    //starting with the second character. 
    //For example, the call encryptTwoKeys(“First Legion”, 23, 17) 
    //should return “Czojq Ivdzle”. 
    //‘F’ is encrypted with key 23, 
    //the first ‘i’ with 17,
    //the ‘r’ with 23, 
    //and the ‘s’ with 17, etc. Be sure to test this method. 

