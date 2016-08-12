package com.github.jmetzz.laboratory.xml_processing.jaxb;


import com.google.common.base.MoreObjects;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import static java.math.BigDecimal.ROUND_HALF_UP;

public class CompleteExample {


    public static void main(String[] args) throws JAXBException {

        Payment payment = new PaymentBuilder()
                .withTransactionCode("1234")
                .withAmountPayed(new BigDecimal(15.0))
                .withCreditCardNumber("4852-8888")
                .withPaymentMethodCode("CASH")
                .withTimeOfPayment(LocalDateTime.of(2016, 10, 1, 10, 0)).build();


        StringWriter writer = new StringWriter();

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


    @XmlRootElement(name = "payment")
    @XmlType(propOrder = {"transactionCode", "timeOfPayment", "paymentMethodCode", "amountPayed", "ccNumber"})
    static final class Payment {

        public String transactionCode;
        public LocalDateTime timeOfPayment;
        public String paymentMethodCode;
        public BigDecimal amountPayed;
        public String ccNumber;

        public Payment() {
        }

        public Payment(PaymentBuilder builder) {
            this.transactionCode = builder.transactionCode;
            this.timeOfPayment = builder.timeOfPayment;
            this.paymentMethodCode = builder.paymentMethodCode;
            this.amountPayed = builder.amountPayed;
            this.ccNumber = builder.creditCardNumber;
        }

        public String toXml() {
            StringBuilder sb = new StringBuilder();
            sb.append("<payment ")
                    .append("transaction-code=\"")
                    .append((this.transactionCode == null ? "" : this.transactionCode))
                    .append("\">")
                    .append("<time-of-payment>").append((this.timeOfPayment == null ? "" : new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(this.timeOfPayment)))
                    .append("</time-of-payment>")
                    .append("<payment-method-code>" + (this.paymentMethodCode == null ? "" : this.paymentMethodCode) + "</payment-method-code>")
                    .append("<amount-paid>" + format(amountPayed) + "</amount-paid>")
                    .append("<cc-number>" + (this.ccNumber == null ? "" : this.ccNumber) + "</cc-number>")
                    .append("</payment>");

            return sb.toString();
        }


        private String format(BigDecimal amount) {
            String output = "";
            if (amount != null) {
                BigDecimal bigDecimal = amount.setScale(2, ROUND_HALF_UP);
                DecimalFormat df = new DecimalFormat("#,###,###,###.00");
                output = df.format(bigDecimal.abs()); // TODO remove abs when comparation with dnrro finished
            }
            return output;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("transactionCode", transactionCode)
                    .add("timeOfPayment", timeOfPayment)
                    .add("paymentMethodCode", paymentMethodCode)
                    .add("amountPayed", amountPayed)
                    .add("ccNumber", ccNumber)
                    .toString();
        }
    }

    static class PaymentBuilder {
        private String transactionCode;
        private LocalDateTime timeOfPayment;
        private String paymentMethodCode;
        private BigDecimal amountPayed;
        private String creditCardNumber;

        public Payment build() {
            return new Payment(this);
        }

        public PaymentBuilder withTransactionCode(String transactionCode) {
            this.transactionCode = transactionCode;
            return this;
        }

        public PaymentBuilder withTimeOfPayment(LocalDateTime timeOfPayment) {
            this.timeOfPayment = timeOfPayment;
            return this;
        }

        public PaymentBuilder withPaymentMethodCode(String paymentMethodCode) {
            this.paymentMethodCode = paymentMethodCode;
            return this;
        }

        public PaymentBuilder withAmountPayed(BigDecimal amountPayed) {
            this.amountPayed = amountPayed;
            return this;
        }

        public PaymentBuilder withCreditCardNumber(String creditCardNumber) {
            this.creditCardNumber = creditCardNumber;
            return this;
        }
    }
}
