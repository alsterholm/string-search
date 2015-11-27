package tests;

import utils.TextFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 2015-11-26.
 */
public class TESTCLASS {
    int B = 256;
    int Q = 23291;
    String T = "";
    String P = "";
    int N;
    int M;
    List<Integer> pos;

    public void test (String T, String P, List<Integer> pos) {
        this.T = T;
        this.P = P;
        N = T.length();
        M = P.length();
        this.pos = pos;
        long time = System.nanoTime();
        double pHash = hash(P, M); // pattern hash
        double tHash = hash(T, M); // hash of first substring i text
//        System.out.println("PATTERN HAS = " + pHash);
        int matches = 0; // hash matches

        // if hash match at pos 0 in T
        if (pHash == tHash) {
            matches++;
            check(T, 0); // check all the chars in P agains chars in T
        }

        for (int i = M; i < N; i++ ) {
//            System.out.println("The text hash before rehash: " + tHash);
            tHash = (tHash + Q - Math.pow(B, M-1) * T.charAt(i-M) % Q) % Q;
//            System.out.println("The text hash after remove rehash: " + tHash + "\n\n");

            tHash = (tHash * B + T.charAt(i)) % Q;
//            System.out.println("The text hash after adding rehash: " + tHash + "\n\n");

            int offset = i - M + 1;
            if (pHash == tHash) {
                matches++;
                check(T, offset);
            }
        }
        time = (System.nanoTime()-time) / 1000000;
//        for (int i: pos) {
//            System.out.println("Match found at posistion: " + i);
//        }
        System.out.println("Search took: " + time + " ms. And number of matches in hash: " + matches);
    }

    public boolean check (String txt, int i) {
        for (int j = 0; j < M; j++) {
            if (P.charAt(j) != T.charAt(i+j)) {
                return false;
            }
        }
        pos.add(i);
        return true;
    }

    public int hash(String sub, int M) {
        int h = 0;
        for (int i = 0; i < M; i++) {
            h= (h * B + sub.charAt(i)) % Q;
//            System.out.println("hashing number " + i + " is "  +h);
        }
        return h;
    }

    public static void main(String[] args) throws IOException {
        TESTCLASS test = new TESTCLASS();
        String P = "sorry";
        System.out.println(P.length());
        String T = new String(TextFileReader.readFile("C:\\Users\\Jimmy\\test.txt"));
//        test.test("Tsting sdfsdfdsfsdfsdfsndfksdnkfsdnfkskfsdfnskdnskdjnfsdknfksdjfksdnsndfnsdkfnsdkfjnskjfnsdkjfnsdknfksdnfksdjnfkjsdnknsdnsddsf sdsti, dsfs dfsdfsdfsfs sdf sdfsfsfdsti", "sti", new ArrayList<Integer>());
        test.test(T, "sorry", new ArrayList<Integer>());
    }
}
