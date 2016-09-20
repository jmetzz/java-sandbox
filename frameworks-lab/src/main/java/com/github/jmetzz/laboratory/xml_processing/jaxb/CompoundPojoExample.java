package com.github.jmetzz.laboratory.xml_processing.jaxb;


import com.github.jmetzz.laboratory.xml_processing.pojos.PurchaseOrderType;
import com.github.jmetzz.laboratory.xml_processing.pojos.USAddress;

import javax.xml.bind.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static com.github.jmetzz.laboratory.Constants.XML_TEST_RESOURCES;

public class CompoundPojoExample {


    public static void main(String[] args) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance("com.github.jmetzz.laboratory.xml_processing.pojos");
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            String inputFile = XML_TEST_RESOURCES + "purchaseorder.xml";

            PurchaseOrderType purchaseOrderType = new PurchaseOrderType();


            JAXBElement purchaseOrderElement = (JAXBElement) unmarshaller.unmarshal(new FileInputStream(inputFile));
            PurchaseOrderType pOrder = (PurchaseOrderType) purchaseOrderElement.getValue();

            USAddress address = pOrder.getBillTo();
            address.setName("Isaac Newton");

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(purchaseOrderElement, System.out);

        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
