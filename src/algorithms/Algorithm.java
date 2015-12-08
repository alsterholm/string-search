package algorithms;

import java.util.ArrayList;

/**
 * Generic interface for string searching algorithms.
 *
 * @author Jimmy Lindström (ae7220)
 * @author Andreas Indal (ae2922)
 */
public interface Algorithm {
    /**
     * Run the algorithm. The algorithm should search for
     * the string P (pattern) inside the string T (text),
     * and record the indices all occurrences inside the
     * ArrayList pos.
     *
     * @param T String to search inside.
     * @param P String to find.
     * @param pos ArrayList to add indices to.
     * @return The indices of all finds.
     */
    ArrayList<Integer> run(String T, String P, ArrayList<Integer> pos);

    /**
     * Should return the runtime of the algorithm in
     * milliseconds.
     *
     * @param includePreprocessing Flag for whether to include preprocessing time or not.
     * @return The algorithm runtime.
     */
    double getRuntime(boolean includePreprocessing);
}
