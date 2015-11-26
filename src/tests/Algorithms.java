package tests;

import utils.*;
import algorithms.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by andreas on 2015-11-25.
 */
public class Algorithms {

    public static void test() {
        Algorithm a = new NaiveStringSearch();
        ArrayList<Integer> pos = new ArrayList<Integer>();
        String T = "";

        try {
            T = new String(TextFileReader.readFile("2000000.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        long time = System.nanoTime();
        a.run(T, "reprehenderit", pos);
        long runtime = (System.nanoTime() - time) / 1000000;

        System.out.printf("Total matches: %s in %s ms.\n\n", pos.size(), runtime);
        for (int i : pos) {
            System.out.printf("Match found at position %s.\n", i);
        }
    }

    public static void main(String[] args) {
        test();
    }
}
