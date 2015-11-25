package utils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Jimmy on 2015-11-21.
 */
public class TextFileReader {
    private String text;
    private String path;


    public static String chooseFile () {
        JFileChooser fileChooser;
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(new Frame());
        String path = "";
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            path = selectedFile.getAbsolutePath();
        }

        return path;
//        readFile(path, );

    }

    public static char[] readFile(String fnam) throws IOException {
        InputStreamReader r = new InputStreamReader(new FileInputStream(fnam));
        ArrayList<char[]> blocks = new ArrayList<char[]>();
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

    public static void main(String[] args) throws IOException {
        char[] f = readFile(chooseFile());
        for (int i = 0; i < f.length; i++) {
            if (i == 40) {
                System.out.println("… and then "+(f.length-i)+" more characters");
                break;
            }
            System.out.println(i+": "+f[i]+" ("+(int) f[i]+")");
        }
    }
}
