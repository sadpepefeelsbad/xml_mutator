package org.example;

import org.example.MutateStrats.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Mutator {

    String dirPath;
    ArrayList<Document> parsedXMLs;
    ArrayList<Mutation> mutations = new ArrayList<>();

    public Mutator(String dirPath) {
        this.dirPath = dirPath;
        mutations.add(new AddElement());
        //mutations.add(new RemoveElement());
        mutations.add(new AddAttribute());
        mutations.add(new RemoveAttribute());
        mutations.add(new MutateAttribute());
        mutations.add(new MutateElement());
        mutations.add(new AddTextNode());
    }

    public static void createXMLs(ArrayList<Document> mutatedXMLs) throws TransformerException {
        int nameId = 1;
        for (Document mutatedXML : mutatedXMLs) {
            DOMSource domSource = new DOMSource(mutatedXML);
            StreamResult streamResult = new StreamResult(new File(String.format("MutatedXMLs/%d.xml",nameId)));
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(domSource, streamResult);
            nameId++;
        }
    }

    public void initialize() throws IOException, ParserConfigurationException, SAXException {
        ArrayList<String> filesNames = listFilesForFolder(dirPath);
        parsedXMLs = parseXMLs(filesNames);
    }

    public void mutate() throws TransformerException {
        Random random = new Random();
        for (Document doc : parsedXMLs) {
            for (int i = 0; i < 1000; i++) {
                int mutationID = random.nextInt(mutations.size());
                mutations.get(mutationID).mutate(doc);
            }
        }
        createXMLs(parsedXMLs);
    }

    public ArrayList<String> listFilesForFolder(String dirPath) {
        File folder = new File(dirPath);
        ArrayList<String> filesPath = new ArrayList<>();
        for (File fileEntry : folder.listFiles()) {
            filesPath.add(fileEntry.getName());
        }
        return filesPath;
    }

    public ArrayList<Document> parseXMLs(ArrayList<String> fileNames) throws IOException, SAXException, ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        ArrayList<Document> parsedXMLs = new ArrayList<>();

        for (String fileName : fileNames) {
            Document doc = builder.parse(new File(dirPath + "/" + fileName));
            parsedXMLs.add(doc);
        }
        return parsedXMLs;
    }

}
