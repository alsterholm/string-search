package tests;

import utils.*;
import algorithms.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by andreas on 2015-11-25.
 */
public class Algorithms {

    public static void naiveStringSearch () {
        String p = "Lorem";
        char[] haystack = null;
        try {
            haystack = TextFileReader.readFile(TextFileReader.chooseFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (char c: haystack) {
            System.out.println(c);
        }
        char[] needle = p.toCharArray();
        ArrayList<Integer> pos = new ArrayList<>();
        pos = NaiveStringSearch.run(haystack, needle, pos);
        for (Integer i: pos) {
            System.out.println("Needle can be found in haystack at position: " + i);
        }
    }

    public static void main(String[] args) {
        naiveStringSearch();
    }
}
