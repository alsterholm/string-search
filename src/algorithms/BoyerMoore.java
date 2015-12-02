package algorithms;

import java.util.ArrayList;

/**
 * Created by andreas on 2015-12-02.
 */
public class BoyerMoore implements Algorithm {
    private final int R = 256;
    private double runtime;

    @Override
    public ArrayList<Integer> run(String T, String P, ArrayList<Integer> pos) {
        int[] right = new int[R];
        for (int i = 0; i < R; i++) right[i] = -1;

        // Set the offset value for every character in the pattern
        for (int i = 0; i < P.length(); i++) right[P.charAt(i)] = i;

        int M = P.length();
        int N = T.length();

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

        return pos;
    }

    @Override
    public double getRuntime() {
        return runtime;
    }
}
