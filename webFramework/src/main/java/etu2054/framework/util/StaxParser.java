package etu2054.framework.util;




import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StaxParser {

    public StaxParser() {
    }

    @SuppressWarnings({"null" })
    public String getConfig(String configFile) throws XMLStreamException, FileNotFoundException {
        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = getClass().getResourceAsStream(configFile);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            String path = "";
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // If we have an item element, we create a new item
                    String elementName = startElement.getName().getLocalPart();
                    switch (elementName) {
                        case "Web":
                            // We read the attributes from this tag and add the date
                            // attribute to our object
                            break;
                        case "Package":
                            event = eventReader.nextEvent();
                            path = event.asCharacters().getData();
                            break;
                    }
                }

            }
            return path;
        } catch (XMLStreamException e) {
            e.printStackTrace();
            System.out.println("tsy hita");
            throw e;
        }
    }

}