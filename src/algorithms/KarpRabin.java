package algorithms;

import utils.TextFileReader;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jimmy on 2015-11-27.
 */
public class KarpRabin implements Algorithm {
    private long B = 256;
    private long Q = BigInteger.probablePrime(31, new Random()).longValue();
    private long E = 1;
    private int N;
    private int M;
    private double runtime;

    public ArrayList<Integer> run(String T, String P, ArrayList<Integer> pos) {
        N = T.length();
        M = P.length();

        long pHash = hash(P, M); // pattern hash
        long tHash = hash(T, M); // hash of first substring i text
        int matches = 0; // hash matches

        // Precompute exponent
        for (int i = 1; i <= M - 1; i++)
            E = (B * E) % Q;

        this.runtime = System.nanoTime();

        // if hash match at pos 0 in T
        if (pHash == tHash && check(T, P, 0)) {
            matches++;
            pos.add(0);
        }

        for (int i = M; i < N; i++ ) {
            tHash = ((tHash + Q - E * T.charAt(i-M) % Q) % Q);
            tHash = (tHash * B + T.charAt(i)) % Q;

            int offset = i - M + 1;
            if (pHash == tHash && check(T, P, offset)) {
                matches++;
                pos.add(offset);
            }
        }
        this.runtime = (System.nanoTime() - this.runtime) / 1000000;
        System.out.printf("Amount of hash matches: %s\n", matches);

        return pos;
    }

    public double getRuntime() {
        return runtime;
    }

    public boolean check(String T, String P, int i) {
        for (int j = 0; j < M; j++) {
            if (P.charAt(j) != T.charAt(i+j)) {
                return false;
            }
        }
        return true;
    }

    public long hash(String txt, int M) {
        long h = 0;
        for (int i = 0; i < M; i++) {
            h= (h * B + txt.charAt(i)) % Q;
        }
        return h;
    }
}
