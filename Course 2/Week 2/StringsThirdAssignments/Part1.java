
/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class Part1 {
    
     public int findStopCodon(
            String dnaStr,
            int startIndex,
            String stopCodon
    ) {
        int currIndex = dnaStr.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            int diff = currIndex - startIndex;
            if (diff % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dnaStr.length();
    }

    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = 0;

        if (taaIndex == -1 ||
                (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }

        if (minIndex == -1 ||
                (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        } else {
            minIndex = taaIndex;
        }

        if (minIndex == -1) {
            return "";
        }

        return dna.substring(startIndex, minIndex + 3);
    }

    public void printAllGenes(String dna) {
        int startIndex = 0;
        //Repeat the following steps
        while (true) {
            //Find the next gene after startIndex
            String currentGene = findGene(dna, startIndex);
            //If no gene was found, leave this loop if (
            if (currentGene.isEmpty()) {
                break;
            }

            System.out.println(currentGene);

            startIndex = dna.indexOf(currentGene, startIndex) +
                    currentGene.length();
        }
    }

    public void testFindStop() {
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = findStopCodon(dna, 0, "TAA");
        if (dex != 9) {
            System.out.println("error on 9");
        }

        dex = findStopCodon(dna, 9, "TAA");
        if (dex != 21) {
            System.out.println("error on 21");
        }

        dex = findStopCodon(dna, 1, "TAA");
        if (dex != 26) {
            System.out.println("error on 26");
        }

        dex = findStopCodon(dna, 0, "TAG");
        if (dex != 26) {
            System.out.println("error on 26");
        }
        System.out.println("Tests finished, you're good");
    }

    public void testFindGene() {
        String dna = "ATGCCCGGGAAATAACCC";
        String gene = findGene(dna, 0);
        System.out.println(gene);
        if (!gene.equals("ATGCCCGGGAAATAA")) {
            System.out.println("error");
        }
        System.out.println("tests finished");
    }
}