package com.github.jmetzz.frameworksLab.xml_processing.sax;

import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SaxParsingTest {
    public static final String PACKAGE_RESOURCES = "src/test/resources/xml-resources/";

    @Test
    public void shouldParseOrderlines() throws ParserConfigurationException, SAXException, IOException {
        SaxParsing saxParser = new SaxParsing();
        List<OrderLine> parseOrderLines = saxParser.parseOrderLines(PACKAGE_RESOURCES + "order.xml");
        assertEquals(2, parseOrderLines.size());
        OrderLine orderLine = parseOrderLines.get(0);
        assertEquals("H2G2", orderLine.getItem());
        assertEquals((Integer) 1, orderLine.getQuantity());
        assertEquals((Double) 23.5, orderLine.getUnitPrice());
        orderLine = parseOrderLines.get(1);
        assertEquals("Harry Potter", orderLine.getItem());
        assertEquals((Integer) 2, orderLine.getQuantity());
        assertEquals((Double) 34.99, orderLine.getUnitPrice());

        System.out.println(saxParser.getOrderlines());
    }

}