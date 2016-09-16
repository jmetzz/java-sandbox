package com.github.jmetzz.laboratory.xml_processing.jaxb;


import com.github.jmetzz.laboratory.xml_processing.pojos.Payment;
import com.github.jmetzz.laboratory.xml_processing.pojos.PaymentBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * After unmarshalling, your program can access and display the data in the XML document
 * simply by accessing the data in the Java content objects and then displaying it.
 * There is no need to create and use a parser and no need to write a content handler
 * with callback methods. What this means is that developers can access and process XML data
 * without having to know XML or XML processing.
 */
public class SimplePojoExample {


    public static void main(String[] args) throws JAXBException {

        Payment payment = new PaymentBuilder()
                .withTransactionCode("1234")
                .withAmountPayed(new BigDecimal(15.0))
                .withCreditCardNumber("4852-8888")
                .withPaymentMethodCode("CASH")
                .withTimeOfPayment(LocalDateTime.of(2016, 10, 1, 10, 0)).build();


        StringWriter writer = new StringWriter();

        /* This object provides the entry point to the JAXB API */
        JAXBContext context = JAXBContext.newInstance(Payment.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        marshaller.marshal(payment, writer);

        System.out.print("Payment instance: ");
        System.out.println(payment);
        System.out.println("\nJAXB generate xml string representation:");

        System.out.println(writer.toString());
        System.out.println("\n\nVerify that JAXB generates the same string we created manually (ideally this should be tested with unit test):");
        System.out.println("\tSame as toXml() object method?\n\t" + (writer.toString().equals(writer.toString())));

    }

}
