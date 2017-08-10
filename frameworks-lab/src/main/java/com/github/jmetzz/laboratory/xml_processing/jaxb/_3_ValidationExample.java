package com.github.jmetzz.laboratory.xml_processing.jaxb;


import com.github.jmetzz.laboratory.xml_processing.pojos.PurchaseOrderType;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static com.github.jmetzz.laboratory.Constants.XML_TEST_RESOURCES;

public class _3_ValidationExample {


    public static void main(String[] args) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance("com.github.jmetzz.laboratory.xml_processing.pojos");
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            /*
             * We can use schema validation here to verify that our input is correct according to a
             * predefined schema. In the old days (before JAXB2.0) we would use the setValidating(true) method,
             * which is now deprecated.
             * However, now you can use the unmarshaller.setSchema() method.
             */

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(XML_TEST_RESOURCES + "purchaseorder.xsd"));
            unmarshaller.setSchema(schema);

            System.out.println("Unmarshalling a correct input file with purchase order information");

            JAXBElement purchaseOrderElement = (JAXBElement) unmarshaller.unmarshal(new FileInputStream(XML_TEST_RESOURCES + "purchaseorder.xml"));
            PurchaseOrderType pOrder = (PurchaseOrderType) purchaseOrderElement.getValue();

            System.out.println("\tWho is going to pay? "+ pOrder.getBillTo().getName());


            System.out.println("Unmarshalling a not correct input file with purchase order information");


            purchaseOrderElement = (JAXBElement) unmarshaller.unmarshal(new FileInputStream(XML_TEST_RESOURCES + "purchaseorder-with-error.xml"));
            pOrder = (PurchaseOrderType) purchaseOrderElement.getValue();

            System.out.println("\tWho is going to pay? "+ pOrder.getBillTo().getName());

        } catch (UnmarshalException e) {
            System.out.println("\tUnmarshall failed. ");
            System.out.println("\t" + e.getCause());
        } catch (SAXException | JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
