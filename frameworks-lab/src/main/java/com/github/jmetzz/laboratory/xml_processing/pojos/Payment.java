package com.github.jmetzz.laboratory.xml_processing.pojos;

import com.google.common.base.MoreObjects;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import static java.math.BigDecimal.ROUND_HALF_UP;

@XmlRootElement(name = "payment")
@XmlType(propOrder = {"transactionCode", "timeOfPayment", "paymentMethodCode", "amountPayed", "ccNumber"})
public class Payment {

    public String transactionCode;
    public LocalDateTime timeOfPayment;
    public String paymentMethodCode;
    public BigDecimal amountPayed;
    public String ccNumber;

    public Payment() {
    }

    public Payment(String transactionCode, LocalDateTime timeOfPayment, String paymentMethodCode, BigDecimal amountPayed, String creditCardNumber) {
        this.transactionCode = transactionCode;
        this.timeOfPayment = timeOfPayment;
        this.paymentMethodCode = paymentMethodCode;
        this.amountPayed = amountPayed;
        this.ccNumber = creditCardNumber;
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

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public LocalDateTime getTimeOfPayment() {
        return timeOfPayment;
    }

    public void setTimeOfPayment(LocalDateTime timeOfPayment) {
        this.timeOfPayment = timeOfPayment;
    }

    public String getPaymentMethodCode() {
        return paymentMethodCode;
    }

    public void setPaymentMethodCode(String paymentMethodCode) {
        this.paymentMethodCode = paymentMethodCode;
    }

    public BigDecimal getAmountPayed() {
        return amountPayed;
    }

    public void setAmountPayed(BigDecimal amountPayed) {
        this.amountPayed = amountPayed;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    private String format(BigDecimal amount) {
        String output = "";
        if (amount != null) {
            BigDecimal bigDecimal = amount.setScale(2, ROUND_HALF_UP);
            DecimalFormat df = new DecimalFormat("#,###,###,###.00");
            output = df.format(bigDecimal.abs());
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
