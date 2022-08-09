package com.kuehne.nagel.decathlon.utils;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlUtil {

    public static Element addElement(Document document, Element element, String fieldName, String fieldValue) {

        Element field = document.createElement(fieldName);
        field.appendChild(document.createTextNode(fieldValue));
        element.appendChild(field);
        return field;
    }


    public static void addAttribute(Document document, Element element, String name, String value) {

        Attr attr = document.createAttribute(name);
        attr.setValue(value); //atletname
        element.setAttributeNode(attr);
    }

}
