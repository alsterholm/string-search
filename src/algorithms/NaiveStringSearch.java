package algorithms;

import java.io.IOException;
import java.util.ArrayList;
import utils.*;

/**
 * Created by Jimmy on 2015-11-10.
 */
public class NaiveStringSearch implements Algorithm {
    private double runtime;

    public ArrayList<Integer> run(String T, String P, ArrayList<Integer> pos) {
        char[] t = T.toCharArray();
        char[] p = P.toCharArray();

        int m = t.length;
        int n = p.length;

        this.runtime = System.nanoTime();
        //Itererar alla bokstäver i strängen linjärt t.o.m längden på stängen -(minus) den sökta strängens längd
        for (int i = 0; i <= m - n; i++) {
            int j = 0;
            // Kollar ifall bokstav matchar o loopar då upp till längden av sökta strängen gånger
            while (j < n && t[i+j] == p[j]) {
                j++;
                // Lägger till position i listan "pos" om hela "needle" hittas i "haystack"
                if (j >= n) {
                    pos.add(i);
                }
            }
        }
        this.runtime = (System.nanoTime() - this.runtime) / 1000000;
        return pos;
    }

    public double getRuntime() {
        return runtime;
    }
}
