package algorithms;

import java.io.IOException;
import java.util.ArrayList;
import utils.*;

/**
 * Created by Jimmy on 2015-11-10.
 */
public class NaiveStringSearch {

    public static ArrayList<Integer> run(char[] haystack, char[] needle, ArrayList<Integer> pos) {
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
}
