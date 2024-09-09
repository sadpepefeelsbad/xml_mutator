package org.example;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException {

        String dirPath = "XMLs";
        Mutator mutator = new Mutator(dirPath);
        mutator.initialize();
        mutator.mutate();

    }

}