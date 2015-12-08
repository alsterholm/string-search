package algorithms;

import java.util.ArrayList;

/**
 * Implementation of the Boyer-Moore string search algorithm.
 *
 * @author Jimmy Lindström (ae7220)
 * @author Andreas Indal (ae2922)
 */
public class BoyerMoore implements Algorithm {
    private final int R = 256;

    private int M;
    private int N;

    private double runtime;
    private double extendedRuntime;

    /**
     * The algorithm searches for occurrences of P in T by
     * using a bad character rule. The algorithm is pre-
     * processing the pattern by saving the offset (relative
     * to the end of the string) of all the characters.
     *
     * @param T String to search inside.
     * @param P String to find.
     * @param pos ArrayList to add indices to.
     * @return Indices of all occurrences of P in T.
     */
    @Override
    public ArrayList<Integer> run(String T, String P, ArrayList<Integer> pos) {
        extendedRuntime = System.nanoTime();

        M = P.length();
        N = T.length();

        int[] right = new int[R];
        for (int i = 0; i < R; i++) right[i] = -1;

        // Set the offset value for every character in the pattern
        for (int i = 0; i < M; i++) right[P.charAt(i)] = i;

        int offset;

        runtime = System.nanoTime();
        for (int i = 0; i <= N - M; i += offset) {
            offset = 0;
            for (int j = M-1; j >= 0; j--) {
                // If characters doesn't match, make a jump based on character offset.
                if (P.charAt(j) != T.charAt(i+j)) {
                    offset = Math.max(1, j - right[T.charAt(i+j)]);
                    break;
                }
            }
            // If there is no offset, we have a match.
            if (offset == 0) {
                pos.add(i);
                offset++;
            }
        }
        runtime = (System.nanoTime() - runtime) / 1000000;
        extendedRuntime = (System.nanoTime() - extendedRuntime) / 1000000;

        return pos;
    }

    /**
     * Get the algorithm’s runtime, either with
     * or without preprocessing.
     *
     * @param includePreprocessing Flag for whether to include preprocessing time or not.
     * @return The algorithm’s runtime.
     */
    @Override
    public double getRuntime(boolean includePreprocessing) {
        return includePreprocessing ? extendedRuntime : runtime;
    }
}
