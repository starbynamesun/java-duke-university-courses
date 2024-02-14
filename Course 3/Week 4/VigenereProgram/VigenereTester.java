
/**
 * Write a description of class VigenereTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class VigenereTester {
    
     public void testCaesarCipher() {
        CaesarCipher cc = new CaesarCipher(3);
        FileResource fr = new FileResource("VigenereTestData/titus-small.txt");
        String message = fr.asString();
        String encrypted = cc.encrypt(message);
        String decrypted = cc.decrypt(message);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }

    
    public void vigenereTester() {
    
        VigenereBreaker vb = new VigenereBreaker();
        
        System.out.println(vb.sliceString("abcdefghijklm", 1, 3));
        //sliceString("abcdefghijklm", 0, 3) should return "adgjm"
        //sliceString("abcdefghijklm", 1, 3) should return "behk"
        
        FileResource fr = new FileResource();
        String msg = fr.asString();
        //String eKey = "flute";
        int[] key = vb.tryKeyLength(msg, 38, 'e'); // {5, 11, 20, 19, 4}
        System.out.println(Arrays.toString(key));
        
        
    }
}
