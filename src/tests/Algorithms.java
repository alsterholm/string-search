package tests;

import utils.*;
import algorithms.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by andreas on 2015-11-25.
 */
public class Algorithms {

    public static void test() {
        Algorithm a = new KarpRabin();
        ArrayList<Integer> pos = new ArrayList<Integer>();
        String T = "";

        try {
            T = new String(TextFileReader.readFile("2000000.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        a.run(T, "ipsum", pos);

        System.out.println("!! KARP-RABIN !!");
        System.out.printf("Total matches: %s in %s ms.\n\n", pos.size(), a.getRuntime());

        System.out.println("\n--------------------------------------------------\n");

        Algorithm b = new NaiveStringSearch();
        pos = new ArrayList<Integer>();

        b.run(T, "ipsum", pos);
        System.out.println("!! NAIVE SEARCH !!");
        System.out.printf("Total matches: %s in %s ms.\n\n", pos.size(), b.getRuntime());

        System.out.println("\n--------------------------------------------------\n");

        Algorithm c = new BoyerMoore();
        pos = new ArrayList<Integer>();

        c.run(T, "ipsum", pos);
        System.out.println("!! Boyer-Moore !!");
        System.out.printf("Total matches: %s in %s ms.\n\n", pos.size(), c.getRuntime());

        System.out.println("\n--------------------------------------------------\n");
    }

    public static void main(String[] args) {
        test();
    }
}
