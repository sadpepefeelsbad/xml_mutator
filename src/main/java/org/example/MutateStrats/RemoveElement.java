package org.example.MutateStrats;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RemoveElement extends Mutation {

    @Override
    public void mutate(Document doc) {
        Element randElem = getRandomElement(doc);
        Node parent = randElem.getParentNode();
        NodeList children = randElem.getChildNodes();

        if (Node.ELEMENT_NODE != parent.getNodeType()) {
            return;
        }

        for (int i = 1; i < children.getLength(); i++) {
            parent.appendChild(children.item(i));
        }
        parent.removeChild(randElem);
    }
}
