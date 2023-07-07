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
    public String getFileFolder(InputStream in) throws XMLStreamException, FileNotFoundException {
        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
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
                        case "File-folder":
                            event = eventReader.nextEvent();
                            path = event.asCharacters().getData();
                            break;
                        case "Url":
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
    @SuppressWarnings({"null" })
    public String getConfig(InputStream in) throws XMLStreamException, FileNotFoundException {
        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
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
                        case "Url":
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
    @SuppressWarnings({"null" })
    public String getRequestUrlHeader(InputStream in) throws XMLStreamException, FileNotFoundException {
        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
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
                            break;
                        case "Url":
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