package algorithms;

import java.io.IOException;
import java.util.ArrayList;
import utils.*;

/**
 * Created by Jimmy on 2015-11-10.
 */
public class NaiveStringSearch {

    public static ArrayList<Integer> run(char[] haystack, char[] needle, ArrayList<Integer> pos) {
        int m = haystack.length;
        int n = needle.length;
        long time = System.nanoTime();

        //Itererar alla bokstäver i strängen linjärt t.o.m längden på stängen -(minus) den sökta strängens längd
        for (int i = 0; i <= m - n; i++) {
                int j = 0;
                // Kollar ifall bokstav matchar o loopar då upp till längden av sökta strängen gånger
                while (j < n && haystack[i+j] == needle[j]) {
                    j++;
                    // Lägger till position i listan "pos" om hela "needle" hittas i "haystack"
                    if (j >= n) {
                        pos.add((i+j) - needle.length);
                    }
                }
        }
        time = (System.nanoTime()-time) / 1000000;
        System.out.println("Time for search: " + time + "ms");
        return pos;
    }

    public static void main(String[] args) throws IOException {
        NaiveStringSearch test = new NaiveStringSearch();
        char[] T = TextFileReader.readFile("C:\\Users\\Jimmy\\test.txt");
        String P = "sorry";
//        test.test("Tsting sdfsdfdsfsdfsdfsndfksdnkfsdnfkskfsdfnskdnskdjnfsdknfksdjfksdnsndfnsdkfnsdkfjnskjfnsdkjfnsdknfksdnfksdjnfkjsdnknsdnsddsf sdsti, dsfs dfsdfsdfsfs sdf sdfsfsfdsti", "sti", new ArrayList<Integer>());
        ArrayList<Integer> pos = test.run(T, P.toCharArray(), new ArrayList<Integer>());
        System.out.println("Matches in text: " + pos.size());


    }

}
