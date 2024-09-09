package org.example.MutateStrats;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AddElement extends Mutation {

    @Override
    public void mutate(Document doc) {
        Element randElem = getRandomElement(doc);
        Element newElem = doc.createElement(generateRandomString(true));
        newElem.setTextContent(generateRandomString(false));
        randElem.appendChild(newElem);
    }
}
