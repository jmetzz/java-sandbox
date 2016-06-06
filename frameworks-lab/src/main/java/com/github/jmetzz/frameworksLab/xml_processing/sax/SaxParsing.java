package com.github.jmetzz.frameworksLab.xml_processing.sax;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SaxParsing extends DefaultHandler {


    public static final String ORDER_LINE = "order_line";
    public static final String UNIT_PRICE = "unit_price";
    public static final String QUANTITY = "quantity";
    public static final String ITEM = "item";

    private List<OrderLine> orderlines = new ArrayList<>();

    private OrderLine orderLine;

    private Boolean dealingWithUnitPrice = false;

    private StringBuffer unitPriceBuffer;

    public List<OrderLine> parseOrderLines(String file) throws SAXException, IOException, ParserConfigurationException {
        File xmlDocument = Paths.get(file).toFile();
        // SAX Factory
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        // Parsing the document
        saxParser.parse(xmlDocument, this);
        return orderlines;
    }


    @Override
    public void startElement(String namesspaceURI, String localName, String qualifiedName, Attributes attributes) throws SAXException {
        switch (qualifiedName) {
            case ORDER_LINE:
                orderLine = new OrderLine();
                for (int i = 0; i < attributes.getLength(); i++) {
                    switch (attributes.getLocalName(i)) {
                        case ITEM:
                            orderLine.setItem(attributes.getValue(i));
                            break;
                        case QUANTITY:
                            orderLine.setQuantity(Integer.valueOf(attributes.getValue(i)));
                            break;
                    }
                }
                break;
            case UNIT_PRICE:
                dealingWithUnitPrice = true;
                unitPriceBuffer = new StringBuffer();
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (dealingWithUnitPrice)
            unitPriceBuffer.append(ch, start, length);
    }

    @Override
    public void endElement(String namesspaceURI, String localName, String qualifiedName) throws SAXException {

        switch (qualifiedName) {
            case ORDER_LINE:
                orderlines.add(orderLine);
                break;
            case UNIT_PRICE:
                orderLine.setUnitPrice(Double.valueOf(unitPriceBuffer.toString()));
                dealingWithUnitPrice = false;
                break;
        }
    }


    public List<OrderLine> getOrderlines() {
        return orderlines;
    }
}


