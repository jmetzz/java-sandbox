package com.github.jmetzz.laboratory.xml_processing.pojos;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class PaymentBuilder {
    private String transactionCode;
    private LocalDateTime timeOfPayment;
    private String paymentMethodCode;
    private BigDecimal amountPayed;
    private String creditCardNumber;

    public Payment build() {
        checkNotNull(transactionCode);
        checkNotNull(timeOfPayment);
        checkNotNull(paymentMethodCode);
        checkNotNull(amountPayed);
        checkNotNull(creditCardNumber);

        return new Payment(transactionCode, timeOfPayment, paymentMethodCode, amountPayed, creditCardNumber);
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
