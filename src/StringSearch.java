package STRINGSEARCH;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static STRINGSEARCH.TextFileReader.chooseFile;
import static STRINGSEARCH.TextFileReader.readFile;


/**
 * Created by Jimmy on 2015-11-10.
 */
public class StringSearch {

    public static ArrayList<Integer> findPattern (char[] haystack, char[] needle, ArrayList<Integer> pos) {
        int m = haystack.length;
        int n = needle.length;
        //Itererar alla bokstäver i strängen linjärt t.o.m längden på stängen -(minus) den sökta strängens längd
        for (int i = 0; i <= m - n; i++) {
                int j = 0;
                // Kollar ifall bokstav matchar o loopar då upp till längden av sökta strängen gånger
                while (j < n && haystack[i+j] == needle[j]) {
                    j++;
                    // Lägger till position i listan "pos" om hela "needle" hittas i "haystack"
                    if (j >= n) {
                        pos.add((i+j) - needle.length);
                    }
                }
        }
        return pos;
    }

    public void test () {
        String p = "few";
        char[] haystack = null;
        try {
            haystack = readFile(chooseFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (char c: haystack) {
            System.out.println(c);
        }
        char[] needle = p.toCharArray();
        ArrayList<Integer> pos = new ArrayList<>();
        pos = findPattern(haystack, needle, pos);
        for (Integer i: pos) {
            System.out.println("Needle can be found in haystack at position: " + i);
        }
    }

    public static void main (String[] args) {
        StringSearch search = new StringSearch();
        search.test();
    }
}
