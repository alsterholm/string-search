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
            T = new String(TextFileReader.readFile("20000.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        long time = System.nanoTime();
        a.run(T, "Lorem ipsum", pos);
        long runtime = (System.nanoTime() - time) / 1000000;

        System.out.println("!! KARP-RABIN !!");
        System.out.printf("Total matches: %s in %s ms.\n\n", pos.size(), runtime);
        for (int i : pos) {
            System.out.printf("Match found at position %s.\n", i);
        }

        System.out.println("--------------------------------------------------");
        System.out.println("--------------------------------------------------");

        Algorithm b = new NaiveStringSearch();
        pos = new ArrayList<Integer>();

        time = System.nanoTime();
        b.run(T, "Lorem ipsum", pos);
        runtime = (System.nanoTime() - time) / 1000000;
        System.out.println("!! NAIVE SEARCH !!");
        System.out.printf("Total matches: %s in %s ms.\n\n", pos.size(), runtime);
        for (int i : pos) {
            System.out.printf("Match found at position %s.\n", i);
        }

        System.out.println("--------------------------------------------------");
        System.out.println("--------------------------------------------------");

        Testclass c = new Testclass();
        pos = new ArrayList<Integer>();

        time = System.nanoTime();
        c.test(T, "Lorem ipsum", pos);
        runtime = (System.nanoTime() - time) / 1000000;
        System.out.println("!! Testclass !!");
        System.out.printf("Total matches: %s in %s ms.\n\n", pos.size(), runtime);
        for (int i : pos) {
            System.out.printf("Match found at position %s.\n", i);
        }
    }

    public static void main(String[] args) {
        test();
    }
}
