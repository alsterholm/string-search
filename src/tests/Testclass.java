package tests;

import utils.TextFileReader;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jimmy on 2015-11-27.
 */
public class Testclass {
    int B = 256;
    int Q = 23291;
    String T = "";
    String P = "";
    int N;
    int M;
    ArrayList<Integer> pos;

    public ArrayList<Integer> test (String T, String P, ArrayList<Integer> pos) {
        this.T = T;
        this.P = P;
        N = T.length();
        M = P.length();
        this.pos = pos;
        double pHash = hash(P, M); // pattern hash
        double tHash = hash(T, M); // hash of first substring i text
        int matches = 0; // hash matches

        // if hash match at pos 0 in T
        if (pHash == tHash && check(T, 0)) {
            matches++;
            pos.add(0);
        }

        for (int i = M; i < N; i++ ) {
            tHash = ((tHash + Q - Math.pow(B, M-1) * T.charAt(i-M) % Q) % Q);
            tHash = (tHash * B + T.charAt(i)) % Q;

            int offset = i - M + 1;
            if (pHash == tHash && check(T, offset)) {
                matches++;
                pos.add(offset);
                //check(T, offset);
            }
        }
        System.out.printf("Amount of hash matches: %s\n", matches);

        return pos;
    }

    public boolean check (String txt, int i) {
        for (int j = 0; j < M; j++) {
            if (P.charAt(j) != T.charAt(i+j)) {
                return false;
            }
        }
        return true;
    }

    public int hash(String txt, int M) {
        int h = 0;
        for (int i = 0; i < M; i++) {
            h= (h * B + txt.charAt(i)) % Q;
        }
        return h;
    }

//    public static void main(String[] args) throws IOException {
//        String T = "";
//        try {
//            T = new String(TextFileReader.readFile("2000.txt"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Testclass test = new Testclass();
//        String P = "sorry";
//        long time = System.nanoTime();
//
//        ArrayList<Integer> pos = test.test(T, "em ipsum", new ArrayList<Integer>());
//        long runtime = (System.nanoTime() - time) / 1000000;
//        System.out.printf("Total matches: %s in %s ms.\n\n", pos.size(), runtime);for (int i : pos) {
//            System.out.printf("Match found at position %s.\n", i);
//        }
//
//
//    }
}
