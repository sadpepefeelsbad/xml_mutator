package org.example.MutateStrats;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Random;

public class MutateElement extends Mutation {

    @Override
    public void mutate(Document doc) {
        Element randElem = getRandomElement(doc);
        NodeList children = randElem.getChildNodes();
        ArrayList<Node> textNodes = new ArrayList<>();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Element.TEXT_NODE) {
                textNodes.add(child);
            }
        }
        if (textNodes.size() == 1) {
            textNodes.getFirst().setTextContent(generateRandomString(false));
        } else if (textNodes.size() > 1) {
            Random random = new Random();
            int range = textNodes.size();
            textNodes.get(random.nextInt(range)).setTextContent(generateRandomString(false));
        }
        doc.renameNode(randElem, null, generateRandomString(true));
    }

}
