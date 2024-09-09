package org.example.MutateStrats;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import java.util.Random;

public class MutateAttribute extends Mutation {

    @Override
    public void mutate(Document doc) {
        Element randElem = getRandomElement(doc);
        NamedNodeMap map = randElem.getAttributes();
        if (map.getLength() == 1) {
            Attr attr = (Attr) map.item(0);
            attr.setValue(generateRandomString(false));
        } else if (map.getLength() > 1) {
            Random random = new Random();
            int randAttrID = random.nextInt(map.getLength());
            Attr attr = (Attr) map.item(randAttrID);
            attr.setValue(generateRandomString(false));
        } else {
            randElem.setAttribute(generateRandomString(true), generateRandomString(false));
        }
    }
}
