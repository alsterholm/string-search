package tests;

import utils.*;
import algorithms.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Test class.
 *
 * @author Jimmy Lindström (ae7220)
 * @author Andreas Indal (ae2922)
 */
public class Algorithms {

    public static void test() {
        Algorithm a = new KarpRabin();
        ArrayList<Integer> pos = new ArrayList<>();
        String filename = "aaa.txt"; // Needs to match a filename in the resources package.
        String T = "";
        String P = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        try {
            T = new String(TextFileReader.readFile(filename));
            // P = new String(TextFileReader.readFile(filename));
        } catch (IOException e) {}

        a.run(T, P, pos);

        System.out.println("!! KARP-RABIN !!");
        System.out.printf("Total matches: %s in %.2f ms (%.2f including preprocessing).\n\n",
                pos.size(), a.getRuntime(false), a.getRuntime(true));

        System.out.println("\n--------------------------------------------------\n");

        Algorithm b = new NaiveStringSearch();
        pos = new ArrayList<>();

        b.run(T, P, pos);
        System.out.println("!! NAIVE SEARCH !!");
        System.out.printf("Total matches: %s in %.2f ms (%.2f including preprocessing).\n\n",
                pos.size(), b.getRuntime(false), b.getRuntime(true));

        System.out.println("\n--------------------------------------------------\n");

        Algorithm c = new BoyerMoore();
        pos = new ArrayList<>();

        c.run(T, P, pos);
        System.out.println("!! Boyer-Moore !!");
        System.out.printf("Total matches: %s in %.2f ms (%.2f including preprocessing).\n\n",
                pos.size(), c.getRuntime(false), c.getRuntime(true));
    }

    public static void main(String[] args) {
        test();
    }
}
