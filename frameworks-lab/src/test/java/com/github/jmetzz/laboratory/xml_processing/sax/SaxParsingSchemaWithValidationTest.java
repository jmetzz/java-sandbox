package com.github.jmetzz.laboratory.xml_processing.sax;

import org.junit.Test;
import org.xml.sax.SAXException;


public class SaxParsingSchemaWithValidationTest {
    public static final String PACKAGE_RESOURCES = "src/test/resources/xml-resources/";

    @Test
    public void shouldParseOutput() throws Exception {
        SaxParsingSchemaWithValidation saxParser = new SaxParsingSchemaWithValidation();
        saxParser.parseOrderXML(PACKAGE_RESOURCES + "order.xml", PACKAGE_RESOURCES + "order.xsd");
        System.out.println(saxParser.getOutput());
    }


    @Test(expected = SAXException.class)
    public void shouldRaiseExcelption() throws Exception {
        SaxParsingSchemaWithValidation saxParser = new SaxParsingSchemaWithValidation();
        saxParser.parseOrderXML(PACKAGE_RESOURCES + "invalidOrder.xml", PACKAGE_RESOURCES + "order.xsd");
    }

}