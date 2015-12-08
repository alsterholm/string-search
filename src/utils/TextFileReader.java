package utils;

import resources.Resource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Handles file reading.
 *
 * @author Jimmy Lindström (ae7220)
 * @author Andreas Indal (ae2922)
 */
public class TextFileReader {
    public static char[] readFile(String path) throws IOException {
        InputStreamReader r = new InputStreamReader(Resource.class.getResourceAsStream(path));
        ArrayList<char[]> blocks = new ArrayList<>();
        int bytes = 0;
        char[] buf = new char[8192];
        int i = 0;
        while (true) {
            int bytesRead = r.read(buf, i, buf.length-i);
            if (bytesRead < 0) { break; } // end of file
            i += bytesRead;
            bytes += bytesRead;
            if (bytes < 0) {
                throw new ArrayIndexOutOfBoundsException("File too big");
            }
            if (i == buf.length) {
                blocks.add(buf);
                buf = new char[buf.length];
                i = 0;
            }
        }
        char[] a = new char[bytes]; // breaks if file is 2^31 chars or more
        int k = 0;
        for (char[] b : blocks) {
            for (int j = 0; j < b.length; j++) {
                a[k++] = b[j];
            }
        }
        for (int j = 0; j < i; j++) {
            a[k++] = buf[j];
        }
        r.close();
        return a;
    }
}
