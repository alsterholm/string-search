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
    public String T;
    public String P;

    private int N;
    private int M;
    private long H;

    public ArrayList<Integer> run(String T, String P, ArrayList<Integer> pos) {
        this.T = T;
        this.P = P;

        N = P.length();
        M = T.length();

        RM = 1;
        for (int i = 1; i <= N-1; i++)
            RM = (R * RM) % Q;

        H = hash(P);
        long h = hash(T);
        int matches = 0;

        runtime = System.nanoTime();
        for (int i = N; i < M; i++) {
//            String t = T.substring(i, i+N);

            h = nextHash(T, i, h);
            int offset = i - N + 1;
            if (H == h) {
                matches++;
                if (walkthrough(T, offset)) {
                    pos.add(offset);
                }
            }
        }
        runtime = (System.nanoTime() - runtime) / 1000000;

        System.out.printf("Amount of hash matches: %s\n", matches);
        return pos;
    }
    public boolean walkthrough (String txt, int i) {
        for (int j = 0; j < N; j++) {
            if (P.charAt(j) != T.charAt(i+j)) {
                return false;
            }
        }
        return true;
    }

//    private boolean walkthrough(String T, String P) {
//        char[] t = T.toCharArray();
//        char[] p = P.toCharArray();
//
//        for (int i = 0; i < t.length; i++)
//            if (t[i] != p[i])
//                return false;
//
//        return true;
//    }

    private long hash(String s) {
        long h = 0;

        for (int i = 0; i < N; i++)
            h = (R * h + s.charAt(i)) % Q;

        return h;
    }

    private long nextHash(String s, int i, long h) {
        h = (long)(h + Q - Math.pow(R, N-1) * s.charAt(i - N) % Q) % Q;
        h = (h * R + s.charAt(i)) % Q;

        return h;
    }
}
