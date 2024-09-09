package org.example.MutateStrats;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Random;

public abstract class Mutation {

    public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String lower = upper.toLowerCase();
    public static final String letters = lower + upper;
    public static final String digits = "0123456789";
    public static final String symbols = "!#$%*+,-./:;=?@\n\\^`|~";
    public static final String attrNamePool = letters + digits;
    public static final String attrValuePool = attrNamePool + symbols;

    public Mutation() {
    }

    public abstract void mutate(Document doc);

    public Element getRandomElement(Document doc) {
        Element root = doc.getDocumentElement();
        ArrayList<Element> elements = new ArrayList<>();
        elements.add(root);
        traverseTree(root, elements);

        Random random = new Random();
        int range = random.nextInt(elements.size());
        return elements.get(range);
    }

    public String generateRandomString(boolean isAttrName) {

        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        int range = random.nextInt(1, 20);

        if (isAttrName) {
            stringBuilder.append(letters.charAt(random.nextInt(letters.length())));
            for (int i = 0; i < range; i++) {
                stringBuilder.append(attrNamePool.charAt(random.nextInt(attrNamePool.length())));
            }
        } else {
            for (int i = 0; i < range; i++) {
                stringBuilder.append(attrValuePool.charAt(random.nextInt(attrValuePool.length())));
            }
        }
        return stringBuilder.toString();
    }

    public void traverseTree(Node node, ArrayList<Element> elements) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node_ = nodeList.item(i);
            if (Node.ELEMENT_NODE == node_.getNodeType()) {
                elements.add((Element) node_);
                traverseTree(node_, elements);
            }
        }
    }
}
