package by.tc.task03.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class XMLReader {

    public static String readXML(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String xmlData = null;
        while (reader.ready()) {
            xmlData += (char) reader.read();
        }
        reader.close();
        return xmlData;
    }
}
