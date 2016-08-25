package com.github.jmetzz.laboratory.xml_processing.sax.complexExample;

import java.io.Serializable;

public class ClearingSystem implements Serializable {

	static final long serialVersionUID = 7968446023634800028L;
	
	private static final String ECOM_PAYMENT_OGONE = "/opa-clearing/eComPaymentOgone";
	private static final String ECOM_PAYMENT_BIBIT = "/opa-clearing/eComPaymentBibit";
	private static final String DLINK_OGONE = "/opa-clearing/dLinkOgone";
	private static final String OGONE_QUERY = "/opa-clearing/ogoneQuery";
	
    private String m_name;
    private String m_paymentServerUrl;
    private String m_hostName;
    
    private String m_secondaryUrl;
    private String m_enableSecUrl;
    
	private String m_directpaymenturl;
    private String m_maintenanceUrl;
    private String m_queryUrl;
    
    private boolean withDummyUrls;

	// DIBS parameters
	private String dibsFirstPaymentServerUrl;
	private String dibsSecondPaymentServerUrl;
	private String dibsFirstCheckServerUrl;
	private String dibsSecondCheckServerUrl;
	private String refundServerUrl;
        
    public ClearingSystem() {
    }
    
    public ClearingSystem(String p_name, String p_paymentServerUrl, String p_hostName) {
        m_name = p_name;
        m_paymentServerUrl = p_paymentServerUrl;
        m_hostName = p_hostName;
    }
    
    public static String getDummyOgonePaymentServerUrl(String host) {
    	return host + ECOM_PAYMENT_OGONE;
    }

    public static String getDummyBibitPaymentServerUrl(String host) {
    	return host + ECOM_PAYMENT_BIBIT;
    }
    
    public static String getDummyDirectPaymentUrl(String host) {
    	return host + DLINK_OGONE;
    }
    
    public static String getDummyMaintenanceServerUrl(String host) {
    	return host + DLINK_OGONE;
    }
    
    public static String getDummyQueryServerUrl(String host) {
    	return host + OGONE_QUERY;
    } 
    
    /**
     * Returns the hostName.
     * @return String
     */
    public String getHostName() {
        return m_hostName;
    }

    /**
     * Returns the name.
     * @return String
     */
    public String getName() {
        return m_name;
    }

    /**
     * Sets the hostName.
     * @param p_hostName The hostName to set
     */
    public void setHostName(String p_hostName) {
        m_hostName = p_hostName;
    }

    /**
     * Sets the name.
     * @param p_name The name to set
     */
    public void setName(String p_name) {
        m_name = p_name;
    }

    /**
     * @return
     */
    public String getPaymentServerUrl() {
        return m_paymentServerUrl;
    }

    /**
     * @param p_string
     */
    public void setPaymentServerUrl(String p_string) {
        m_paymentServerUrl = p_string;
    }

	/**
	 * @return
	 */
	public String getEnableSecUrl() {
		return m_enableSecUrl;
	}

	/**
	 * @return
	 */
	public String getSecondaryUrl() {
		return m_secondaryUrl;
	}

	/**
	 * @param string
	 */
	public void setEnableSecUrl(String string) {
		m_enableSecUrl = string;
	}

	/**
	 * @param string
	 */
	public void setSecondaryUrl(String string) {
		m_secondaryUrl = string;
	}

	/**
	 * @return
	 */
	public String getMaintenanceUrl() {
		return m_maintenanceUrl;
	}

	/**
	 * @return
	 */
	public String getQueryUrl() {
		return m_queryUrl;
	}

	/**
	 * @param p_string
	 */
	public void setMaintenanceUrl(String p_string) {
		m_maintenanceUrl = p_string;
	}

	/**
	 * @param p_string
	 */
	public void setQueryUrl(String p_string) {
		m_queryUrl = p_string;
	}

	/**
	 * @return
	 */
	public String getDirectpaymenturl() {
		return m_directpaymenturl;
	}

	/**
	 * @param p_string
	 */
	public void setDirectpaymenturl(String p_string) {
		m_directpaymenturl = p_string;
	}
	
	public boolean isWithDummyUrls() {
		return withDummyUrls;
	}

	public void setWithDummyUrls(boolean p_withDummyUrls) {
		withDummyUrls = p_withDummyUrls;
	}

	public String getDibsFirstPaymentServerUrl() {
		return dibsFirstPaymentServerUrl;
	}

	public void setDibsFirstPaymentServerUrl(String dibsFirstPaymentServerUrl) {
		this.dibsFirstPaymentServerUrl = dibsFirstPaymentServerUrl;
	}

	public String getDibsSecondPaymentServerUrl() {
		return dibsSecondPaymentServerUrl;
	}

	public void setDibsSecondPaymentServerUrl(String dibsSecondPaymentServerUrl) {
		this.dibsSecondPaymentServerUrl = dibsSecondPaymentServerUrl;
	}

	public String getDibsFirstCheckServerUrl() {
		return dibsFirstCheckServerUrl;
	}

	public void setDibsFirstCheckServerUrl(String dibsFirstCheckServerUrl) {
		this.dibsFirstCheckServerUrl = dibsFirstCheckServerUrl;
	}

	public String getDibsSecondCheckServerUrl() {
		return dibsSecondCheckServerUrl;
	}

	public void setDibsSecondCheckServerUrl(String dibsSecondCheckServerUrl) {
		this.dibsSecondCheckServerUrl = dibsSecondCheckServerUrl;
	}

	public String getRefundServerUrl() {
		return refundServerUrl;
	}

	public void setRefundServerUrl(String refundServerUrl) {
		this.refundServerUrl = refundServerUrl;
	}

	/**
	 * @return String representation of ClearingSystem instance
	 * @author exb165
	 */
	@Override
	public String toString() {

		StringBuffer buffer = new StringBuffer("ClearingSystem[");
		buffer.append("name="+getName());
		buffer.append(",paymentUrl="+getPaymentServerUrl());
		buffer.append(",queryUrl="+getQueryUrl());
		buffer.append(",secondaryUrl="+getSecondaryUrl());
		buffer.append(",maintenanceUrl="+getMaintenanceUrl());
		buffer.append(",enableSecUrl="+getEnableSecUrl());
		buffer.append(",hostName="+getHostName());
		buffer.append(",withDummyUrls="+isWithDummyUrls());
		buffer.append(",dibsFirstPaymentServerUrl="+getDibsFirstPaymentServerUrl());
		buffer.append(",dibsSecondPaymentServerUrl="+getDibsSecondPaymentServerUrl());
		buffer.append(",dibsFirstCheckServerUrl="+getDibsFirstCheckServerUrl());
		buffer.append(",dibsSecondCheckServerUrl="+getDibsSecondCheckServerUrl());
		buffer.append(",refundServerUrl="+getRefundServerUrl());
		buffer.append("]");
		
		return buffer.toString();
	}

}
