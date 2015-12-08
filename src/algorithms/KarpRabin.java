package algorithms;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

/**
 * Implementation of the Karp-Rabin string search algorithm.
 *
 * @author Jimmy Lindström (ae7220)
 * @author Andreas Indal (ae2922)
 */
public class KarpRabin implements Algorithm {
    private final long B = 256;
    private final long Q = BigInteger.probablePrime(31, new Random()).longValue();
    private long E = 1;

    private int N;
    private int M;

    private double runtime;
    private double extendedRuntime;

    /**
     * The algorithm searches for occurrences of P in T, by
     * pre-calculating a hash value for P, and evaluating it
     * against hash matches of each of the substrings in T.
     * The hash value is only computed once, and is then
     * recalculated to keep the hashing at constant time. If
     * there's a hash match, the matching substring will be
     * compared character by character to ensure it is a
     * correct match.
     *
     * @param T String to search inside.
     * @param P String to find.
     * @param pos ArrayList to add indices to.
     * @return Indices of all occurrences of P in T.
     */
    public ArrayList<Integer> run(String T, String P, ArrayList<Integer> pos) {
        this.extendedRuntime = System.nanoTime();

        N = T.length();
        M = P.length();

        long pHash = hash(P, M); // Pattern hash
        long tHash = hash(T, M); // Hash of first substring i text

        // Pre-compute exponent
        for (int i = 1; i <= M - 1; i++)
            E = (B * E) % Q;

        this.runtime = System.nanoTime();

        // Check for match at first position.
        if (pHash == tHash && check(T, P, 0)) {
            pos.add(0);
        }

        for (int i = M; i < N; i++ ) {
            // Recalculate the hash value by changing the
            // first and last digits of the last hash.
            tHash = (tHash + Q - E * T.charAt(i-M) % Q) % Q;
            tHash = (tHash * B + T.charAt(i)) % Q;

            int offset = i - M + 1;
            if (pHash == tHash && check(T, P, offset)) {
                pos.add(offset);
            }
        }
        this.runtime = (System.nanoTime() - this.runtime) / 1000000;
        this.extendedRuntime = (System.nanoTime() - this.extendedRuntime) / 1000000;

        return pos;
    }

    /**
     * Get the algorithm’s runtime, either with
     * or without pre-processing.
     *
     * @param includePreprocessing Flag for whether to include pre-processing time or not.
     * @return The algorithm’s runtime.
     */
    @Override
    public double getRuntime(boolean includePreprocessing) {
        return includePreprocessing ? extendedRuntime : runtime;
    }

    /**
     * Check character by character if the string matches. Only
     * called when there's a hash match.
     *
     * @param T Text
     * @param P Pattern
     * @param i Offset in T
     * @return True if match, otherwise false.
     */
    private boolean check(String T, String P, int i) {
        for (int j = 0; j < M; j++) {
            if (P.charAt(j) != T.charAt(i+j)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Creates a hash for the pattern. Also used for the
     * first substring.
     *
     * @param S String to hash
     * @param M Pattern length
     * @return Hash value of S.
     */
    private long hash(String S, int M) {
        long h = 0;
        for (int i = 0; i < M; i++) {
            h= (h * B + S.charAt(i)) % Q;
        }
        return h;
    }
}
