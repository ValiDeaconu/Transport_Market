package org.transexpress.snap.misc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HTMLReader {
    private static final String _404_ERROR_HTML = "<html><body><h2>404 Error: page not found</h2></body></html>";

    public static String read(String fileName) {
        StringBuilder page = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                page.append(line);
            }
        } catch (IOException e) {
            System.err.println("HTMLReader could not read the file: " + e.getMessage());
            return _404_ERROR_HTML;
        }

        return page.toString();
    }

}
