package com.github.jmetzz.laboratory.xml_processing.xstream;


import com.github.jmetzz.laboratory.xml_processing.pojos.*;
import com.thoughtworks.xstream.XStream;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CompoundPojoExample {

    public static void main(String[] args) {

        PaymentSystem paypal = new PaymentSystem("PayPal", "/payment", "http://www.paypal.com");
        Account account = new Account("A", "Dummy account", true, paypal);
        Customer customer = new Customer(6864, "Isaac", "The Tonge Newton", "isaac@the-tonge.newton.com", "+5058973134853");

        Order order = new OrderBuilder()
                .withOrderID("1")
                .withDossier("XS24-5GDT-45775-HSKK44")
                .withDescription("Dummy order")
                .withCreationDate(LocalDateTime.now())
                .withCustomer(customer)
                .withAccount(account)
                .withAlias("Master Green")
                .withAmount(new BigDecimal(105.57))
                .withCurrency("U$")
                .withAlias("")
                .withIssuerId("9348764")
                .withUse3DSecurity(true)
                .createOrder();

        XStream xstream = new XStream();
        String xml = xstream.toXML(order);
        System.out.println(xml);
        Order newOrder = (Order) xstream.fromXML(xml);
        System.out.println(order);

    }


}
