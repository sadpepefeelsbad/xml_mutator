package org.example.MutateStrats;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class AddTextNode extends Mutation {

    @Override
    public void mutate(Document doc) {
        Element randElem = getRandomElement(doc);
        Node newTextNode = doc.createTextNode(generateRandomString(false));
        randElem.appendChild(newTextNode);
    }
}
