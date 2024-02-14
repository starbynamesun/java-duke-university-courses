import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        
        for(int i = whichSlice; i < message.length(); i += totalSlices) {
            sb.append(message.charAt(i));
        }
        
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
                
        CaesarCracker ck = new CaesarCracker(mostCommon);
        
        for(int i = 0; i < klength; i++) { 
            String str = sliceString(encrypted, i, klength);
            key[i] = ck.getKey(str);
        }
        
        return key;
    }
    
    
    public void breakVigenere () {
        FileResource fr = new FileResource();
        String message = fr.asString();
        
        FileResource dictionaryFile = new FileResource("dictionaries/English");
        
        HashSet<String> dictionary = readDictionary(dictionaryFile);
        String decrypted = breakForLanguage(message, dictionary);
        System.out.println(decrypted);
        
        int count = countWords(decrypted, dictionary);
        System.out.println("Valid words: " + count);
        
        //int[] key = tryKeyLength(message, 4, 'e'); // {5, 11, 20, 19, 4}
        //System.out.println("The key is: " + key);
        //VigenereCipher cipher = new VigenereCipher(key);
        //System.out.println(cipher.decrypt(message));
        
        String[] languages = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        HashMap<String, HashSet<String>> dictionaries = new HashMap<String, HashSet<String>>();
        
        for(int i = 0; i < languages.length; i++) {
            FileResource lang = new FileResource("dictionaries/"+languages[i]);
            
            if(!dictionaries.containsKey(languages[i])) {
                dictionaries.put(languages[i], readDictionary(lang));
                System.out.println("Done reading " + languages[i] + " dictionary");
            }
        }
        
        FileResource finale = new FileResource(); //select file to decrypt
        String encrypted = finale.asString();
        breakForAllLangs(encrypted, dictionaries);
        //int count = countWords(decrypted, dictionaries);
        //System.out.println("Valid words: " + count);
    }  
    
    public HashSet<String> readDictionary(FileResource fr) {

        HashSet<String> words = new HashSet<String>();
        
        for(String line : fr.words()) {
            line = line.toLowerCase();
            words.add(line);
        }
        
        return words;
    } 

    public int countWords(String message, HashSet<String> dictionary) {
        
        int count = 0;
        //String[] wordsInMessage = message.split("\\W+");
        
        for(String word : message.split("\\W+")) {
            word = word.toLowerCase();
            
            if(dictionary.contains(word)) {
                count++;
            }
        }
        
        return count;
    }
    
    public String breakForLanguage(String message, HashSet<String> dictionary) {
    
        int keys[] = {};
        VigenereCipher cipher;
        
        String decrypted = "";
        int count = 0;
        int max = 0;
        int keyLength = 0;
        
        for(int i = 1; i <= 100; i++) {
            
            keys = tryKeyLength(message, i, mostCommonCharIn(dictionary));
            cipher = new VigenereCipher(keys);
            
            String decrypt = cipher.decrypt(message);
            count = countWords(decrypt, dictionary);
            if(count > max) {
                max = count;
                decrypted = decrypt;
                keyLength = i;
            }
        }
        System.out.println("Key length with most real words: " + keyLength);
        System.out.println("Count with most real words: " + count);
        
        return decrypted;        
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> letters = new HashMap<Character, Integer>();
        char mostCommonChar = ' ';
        int count = 0;
        
        for(String word : dictionary) {
            for(int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if(!letters.containsKey(ch)) {
                    letters.put(ch, 1);
                } else {
                    letters.put(ch, letters.get(ch) + 1);
                }
            }
        }
        
        for(Character c : letters.keySet()) {
            if(letters.get(c) > count) {
                count = letters.get(c);
                mostCommonChar = c;
            }
        }
        return mostCommonChar;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {

        int maxWords = 0;
        String decryptedMessage = "";
        HashMap<String, Integer> langsMostWords = new HashMap<String, Integer>();
        
        for(String language : languages.keySet()) {
            HashSet<String> dictionary = languages.get(language);
            decryptedMessage = breakForLanguage(encrypted, dictionary);
            int count = countWords(decryptedMessage, dictionary);
            langsMostWords.put(language, count);
        }
        
        for(String lang : langsMostWords.keySet()) {
            if(langsMostWords.get(lang) > maxWords) {
                maxWords = langsMostWords.get(lang);
                System.out.println(decryptedMessage);
                System.out.println("Language: " + lang + " with " + maxWords + " # of words\n");
            }
        }
    }
}

