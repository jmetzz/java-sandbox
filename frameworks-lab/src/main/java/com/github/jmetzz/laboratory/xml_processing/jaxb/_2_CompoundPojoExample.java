package com.github.jmetzz.laboratory.xml_processing.jaxb;



import com.github.jmetzz.laboratory.xml_processing.pojos.ObjectFactory;
import com.github.jmetzz.laboratory.xml_processing.pojos.PurchaseOrderType;
import com.github.jmetzz.laboratory.xml_processing.pojos.USAddress;

import javax.xml.bind.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;

import static com.github.jmetzz.laboratory.Constants.XML_TEST_RESOURCES;

public class _2_CompoundPojoExample {


    public static void main(String[] args) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance("com.github.jmetzz.laboratory.xml_processing.pojos");
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            String inputFile = XML_TEST_RESOURCES + "purchaseorder.xml";

            JAXBElement purchaseOrderElement = (JAXBElement) unmarshaller.unmarshal(new FileInputStream(inputFile));
            PurchaseOrderType pOrder = (PurchaseOrderType) purchaseOrderElement.getValue();

            USAddress address = pOrder.getBillTo();
            address.setName("Isaac Newton");
            address.setZip(new BigDecimal(555555));

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            /*
             * Be aware that you should pass the JAXBElement wrapper object here as the first parameter,
             * not the casted (generated class) object, in this case PurchaseOrderType object pOrder.
             *
             *      marshaller.marshal(pOrder, System.out);    ====> javax.xml.bind.MarshalException
             *
             * Why this?
             * Because PurchaseOrderType class is not always annotated with @XmlRootElement annotation, and
             * therefore, would fire a SaX exception stating a parsing error.
             */

            System.out.println("======== Marshalling based on the original JAXBElement ==========");
            marshaller.marshal(purchaseOrderElement, System.out);


            /* XJC generates the class model and also generates a class called ObjectFactory,
             * which you can use to wrapper a pojo and thus avoid the MarshalException.
             * It handles the XML name and namespace for you, so you don't need to worry about it.
             */

            System.out.println("========  Marshalling based on the ObjectFactory wrapper ==========");
            ObjectFactory factory = new ObjectFactory();
            JAXBElement<PurchaseOrderType> purchaseOrder = factory.createPurchaseOrder(pOrder);
            marshaller.marshal(purchaseOrder, System.out);


        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
