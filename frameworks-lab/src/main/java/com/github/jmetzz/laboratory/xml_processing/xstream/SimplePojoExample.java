package com.github.jmetzz.laboratory.xml_processing.xstream;


import com.github.jmetzz.laboratory.xml_processing.pojos.Payment;
import com.github.jmetzz.laboratory.xml_processing.pojos.PaymentBuilder;
import com.thoughtworks.xstream.XStream;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SimplePojoExample {


    public static void main(String[] args) {

        Payment payment = new PaymentBuilder()
                .withTransactionCode("1234")
                .withAmountPayed(new BigDecimal(15.0))
                .withCreditCardNumber("4852-8888")
                .withPaymentMethodCode("CASH")
                .withTimeOfPayment(LocalDateTime.of(2016, 10, 1, 10, 0)).build();

        XStream xstream = new XStream();

        String xml = xstream.toXML(payment);
        System.out.println(xml);

        Payment newPayment = (Payment) xstream.fromXML(xml);

        System.out.println(newPayment.toString());
    }
}
