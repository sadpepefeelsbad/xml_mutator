package org.example.MutateStrats;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AddAttribute extends Mutation {

    @Override
    public void mutate(Document doc) {
        Element randElem = getRandomElement(doc);
        randElem.setAttribute(generateRandomString(true), generateRandomString(false));
    }
}
