package com.github.jmetzz.laboratory.xml_processing.pojos;

import com.google.common.base.MoreObjects;
import java.io.Serializable;

public class PaymentSystem implements Serializable {

    private static final long serialVersionUID = 7179488618672826604L;
    private String name;
    private String paymentServerUrl;
    private String hostName;

    private String secondaryUrl;
    private String enableSecUrl;


    public PaymentSystem() {
    }

    public PaymentSystem(String name, String paymentServerUrl, String hostName) {
        this.name = name;
        this.paymentServerUrl = paymentServerUrl;
        this.hostName = hostName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaymentServerUrl() {
        return paymentServerUrl;
    }

    public void setPaymentServerUrl(String paymentServerUrl) {
        this.paymentServerUrl = paymentServerUrl;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getSecondaryUrl() {
        return secondaryUrl;
    }

    public void setSecondaryUrl(String secondaryUrl) {
        this.secondaryUrl = secondaryUrl;
    }

    public String getEnableSecUrl() {
        return enableSecUrl;
    }

    public void setEnableSecUrl(String enableSecUrl) {
        this.enableSecUrl = enableSecUrl;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("paymentServerUrl", paymentServerUrl)
                .add("hostName", hostName)
                .add("secondaryUrl", secondaryUrl)
                .add("enableSecUrl", enableSecUrl)
                .omitNullValues()
                .toString();
    }
}
