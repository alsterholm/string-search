package algorithms;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by andreas on 2015-11-25.
 */
public class KarpRabin implements Algorithm {
    public final static long Q = BigInteger.probablePrime(31, new Random()).longValue();
    public final static long R = 256;
    public static long RM;
    public static long runtime = 0;

    private int N;
    private int M;
    private long H;

    public ArrayList<Integer> run(String T, String P, ArrayList<Integer> pos) {
        N = P.length();
        M = T.length();

        RM = 1;
        for (int i = 1; i <= N-1; i++)
            RM = (R * RM) % Q;

        H = hash(P);
        int matches = 0;

        long h = hash(T);

        runtime = System.nanoTime();
        for (int i = N; i < M - N; i++) {
            String t = T.substring(i, i+N);

            h = nextHash(T, i, h);

            if (H == h) {
                matches++;
                if (walkthrough(t, P)) {
                    pos.add(i);
                }
            }
        }
        runtime = (System.nanoTime() - runtime) / 1000000;

        System.out.printf("Amount of hash matches: %s\n", matches);
        return pos;
    }

    private boolean walkthrough(String T, String P) {
        char[] t = T.toCharArray();
        char[] p = P.toCharArray();

        for (int i = 0; i < t.length; i++)
            if (t[i] != p[i])
                return false;

        return true;
    }

    private long hash(String s) {
        long h = 0;

        for (int i = 0; i < N; i++)
            h = (R * h + s.charAt(i)) % Q;

        return h;
    }

    private long nextHash(String s, int i, long h) {
        h = h - ((h / 10000) * 10000);
        h = h * 10;

        return h;
    }
}
