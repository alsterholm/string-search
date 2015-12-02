package algorithms;

import java.util.ArrayList;

/**
 * Created by andreas on 2015-11-25.
 */
public interface Algorithm {
    ArrayList<Integer> run(String T, String P, ArrayList<Integer> pos);
    double getRuntime();
}
