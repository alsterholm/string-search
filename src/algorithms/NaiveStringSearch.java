package algorithms;

import java.io.IOException;
import java.util.ArrayList;
import utils.*;

/**
 * Implementation of a naive string search algorithm.
 *
 * @author Jimmy Lindström (ae7220)
 * @author Andreas Indal (ae2922)
 */
public class NaiveStringSearch implements Algorithm {
    private double runtime;

    /**
     * The algorithm searches naively after all occurrences
     * of P in T.
     *
     * @param T String to search inside.
     * @param P String to find.
     * @param pos ArrayList to add indices to.
     * @return Indices of all occurrences of P in T.
     */
    @Override
    public ArrayList<Integer> run(String T, String P, ArrayList<Integer> pos) {
        char[] t = T.toCharArray();
        char[] p = P.toCharArray();

        int m = t.length;
        int n = p.length;

        runtime = System.nanoTime();
        for (int i = 0; i <= m - n; i++) {
            int j = 0;
            while (j < n && t[i+j] == p[j]) {
                j++;
                if (j >= n)
                    pos.add(i);
            }
        }
        runtime = (System.nanoTime() - runtime) / 1000000;
        return pos;
    }

    /**
     * Get the algorithm’s runtime. Since the naive
     * search doesn't include preprocessing, the
     * includePreprocessing flag is disregarded.
     *
     * @param includePreprocessing Flag for whether to include preprocessing time or not.
     * @return The algorithm’s runtime.
     */
    @Override
    public double getRuntime(boolean includePreprocessing) {
        return runtime;
    }
}
