package com.github.jmetzz.laboratory.xml_processing.sax.complexExample;


import org.apache.log4j.Logger;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ObjectMaker extends DefaultHandler {
/*

    private static final Logger LOGGER = Logger.getLogger(ObjectMaker.class);

    // private static final String KEY_APPLICATIONS = "applications";
    private static final String VERSION_1_0 = "1.0";
    private static final String TESSY_VERSION_1_0_ID = "A963";
    private static final String TESSY_DEF_ACCOUNT = "acc171";
    private static final String NAMESPACE_FEATURE = "http://xml.org/sax/features/namespaces";
    // Buffer for collecting data from
    // the "characters" SAX event.
    private CharArrayWriter contents = new CharArrayWriter();
    private Order order = null;
    // vars for storing the values of applicationCode, accountId and
    // accountConfigId attribs
    // are parsed before objectcreation!
    private String m_applicationId;
    private String paymentOkUrl;
    private String paymentNokUrl;
    private String m_accountConfigId;
    private String m_accountId;
    private String m_orderId;
    private Application m_application = null;
    private boolean m_hasCustomer = false;
    private boolean m_hasShippingAddress = false;
    private Customer m_customer = null;
    private String m_customerName = "";
    private String m_customerAddress = "";
    private String m_customerEmail = "";
    private DeliveryAddress m_shippingAddress = null;
    private static final String WHITESPACE = " ";
    private boolean okNokUrlOverride = false;
    private String m_clientSessionId = "";
    private SegmentParameters cur_segmentParameters = null;

    List<PassengerName> passengerNameList = new ArrayList<PassengerName>();
    List<SegmentParameters> segmentParametersList = new ArrayList<SegmentParameters>();


    public ObjectMaker() {
    }

    public Order makeObject(String p_xml) throws ErrorParsingException {
        try {
            LogCat.debug("be.bene.opa", "ObjectMaker", "makeObject", "OPA",
                    "-> BEGIN makeObject - starttime "
                            + System.currentTimeMillis());
            // Use the default (non-validating) parser
            SAXParser l_saxParser = SAXParserFactory.newInstance().newSAXParser();
            LogCat.debug("be.bene.opa", "ObjectMaker", "makeObject", "OPA",
                    "-> Parserobject created");
            XMLReader xmlReader = l_saxParser.getXMLReader();
            LogCat.debug("be.bene.opa", "ObjectMaker", "makeObject", "OPA",
                    "-> XML reader instantiated" + "-> Is ns aware "
                            + xmlReader.getFeature(NAMESPACE_FEATURE)
                            + "-> Will set ns aware ...");
            xmlReader.setFeature(NAMESPACE_FEATURE, true);
            LogCat.debug("be.bene.opa", "ObjectMaker", "makeObject", "OPA",
                    "-> Is now ns aware???"
                            + xmlReader.getFeature(NAMESPACE_FEATURE));

            // install self as content handler!!!
            xmlReader.setContentHandler(this);

            xmlReader.setErrorHandler(new MyErrorHandler(System.err));
            if (LogCat.isDebugEnabled()) {
                LogCat.debug("be.bene.opa", "ObjectMaker", "makeObject", "OPA",
                        "-> Will parse " + p_xml);
            }

			*/
/*
             * At this point the parsing process will start. Since this very
			 * instance is passed in as ContentHandler you need to check out
			 * startElement and endElement before moving on in the code. It is
			 * in those 2 methods that instance variables like m_order are
			 * initialized!
			 *
			 * @author exb165
			 *//*

            xmlReader.parse(new InputSource(new StringReader(p_xml)));
        } catch (SAXException e) {
            LOGGER.error("-> Error creating the order object", e);
            throw new ErrorParsingException();
        } catch (ParserConfigurationException e) {
            LOGGER.error("-> Error creating the order object", e);
            throw new ErrorParsingException();
        } catch (IOException e) {
            LOGGER.error("-> Error creating the order object", e);
            throw new ErrorParsingException();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("-> Unexpected Error", e);
        }
        LogCat.debug("be.bene.opa", "ObjectMaker", "makeObject", "OPA",
                "-> End of xml parsing");
        // if order had a customer tag
        if (m_hasCustomer) {
            LogCat.debug("be.bene.opa", "ObjectMaker", "makeObject", "OPA",
                    "-> Will set customer name and address");
            m_customer.setName(m_customerName);
            m_customer.setAddress(m_customerAddress);
            order.setCustomer(m_customer);
        }

        //Add passengerNames and segmentParameters
        order.setPassengerNameList(passengerNameList);
        order.setSegmentParametersList(segmentParametersList);

        // fill the orderID attribute
        try {
            // Generate a new orderID
            order.setOrderID(generateKey());
        } catch (InvalidKeyException e) {
            LOGGER.error("-> Invalid key Exception! Cannot obtain an orderkey", e);
        } catch (Exception e) {
            LOGGER.error("-> UNK Exception! Cannot obtain an orderkey", e);
        }
        if (LogCat.isDebugEnabled()) {
            LogCat.debug("be.bene.opa", "ObjectMaker", "makeObject", "OPA",
                    "-> Obtained key " + order.getOrderID());
        }

        LogCat.debug("be.bene.opa", "ObjectMaker", "makeObject", "OPA",
                "-> Get application " + m_applicationId + " from cache ...");

        Application l_application = getApplication(m_applicationId);

        order.setApplication(l_application);
        Account l_account = l_application.getAccount(m_accountId);
        if (l_account != null) {
            LogCat.debug("be.bene.opa", "ObjectMaker", "makeObject", "OPA",
                    "-> Found the cached account for this application");
            order.setAccount(l_account);
        } else {
            LOGGER.warn("-> Could not find account " + m_accountId + " for this application");
            throw new ErrorParsingException();
        }
        LogCat.debug("be.bene.opa", "ObjectMaker", "makeObject", "OPA",
                "ordertype: " + this.getOrderType(order));

        // Configure specific information depending on the type of order
        if (order instanceof BibitOrder) {
            configureBibitOrder((BibitOrder) order);
        } else if (order instanceof OgoneOrder) {
            configureOgoneOrder((OgoneOrder) order);
        } else if (order instanceof TestOrder) {
            ((TestOrder) order).setSelectionPageUrl("/opa/ClearingSimulator?paymentOkUrl="
                    + paymentOkUrl + "&paymentNokUrl=" + paymentNokUrl);
        } else if (order instanceof SaferPayOrder) {
            configureSaferPayOrder((SaferPayOrder) order);
        } else if (order instanceof DibsOrder) {
            configureDibsOrder((DibsOrder) order);
        } else {
            LogCat.debug("be.bene.opa", "ObjectMaker", "makeObject", "OPA  "
                    + order.getDossierNr(), "-> Order ("
                    + ((order != null) ? order.getClass().getName()
                    : "null") + ") is unknown! NOT CASHED ");
        }

        LogCat.debug("be.bene.opa", "ObjectMaker", "makeObject", "OPA "
                + order.getDossierNr(), "-> END - time "
                + System.currentTimeMillis());

        // extra error log for eCom payments
        if (order instanceof BibitOrder || order instanceof OgoneOrder || order instanceof DibsOrder) {
            LOGGER.info("Create new specific order object with transaction id: " + order.getOrderID() + " amount: " + order.getAmount());
        }

        return order;
    }

    private void configureSaferPayOrder(SaferPayOrder l_saferPayOrder) {
        //nothing to do but add the config to the account

        SaferPayAccountConfig l_config = (SaferPayAccountConfig) l_saferPayOrder.getAccount().getConfiguration(m_accountConfigId);
        SaferPayAccountConfig l_newConfig = (SaferPayAccountConfig) l_config.clone();
        l_newConfig.setErrorUrl(urlMaker(l_newConfig.getErrorUrl(),
                paymentNokUrl));
        l_newConfig.setFailureUrl(urlMaker(l_newConfig.getFailureUrl(),
                paymentNokUrl));
        l_newConfig.setSuccessUrl(urlMaker(l_newConfig.getSuccessUrl(),
                paymentOkUrl));
        String l_opaUrl4okNok = getOpaUrl4okNok();
        l_newConfig.setOpaSuccessUrl(l_opaUrl4okNok
                + "?BLName=PaymentOK&from=saferpay&trx=" + order.getOrderID());
        l_newConfig.setOpaFailureUrl(l_opaUrl4okNok
                + "?BLName=PaymentNOK&from=saferpay&trx=" + order.getOrderID());
        l_newConfig.setOpaErrorUrl(l_opaUrl4okNok
                + "?BLName=PaymentError&from=saferpay&trx=" + order.getOrderID());

        LogCat.debug("be.bene.opa", "ObjectMaker", "configureSaferpayOrder",
                "OPA " + order.getDossierNr(),
                "-> opaSuccessUrl = " + l_newConfig.getOpaSuccessUrl());

        l_saferPayOrder.setAccountConfig(l_newConfig);
    }


    private void configureOgoneOrder(OgoneOrder l_ogoneOrder) {
        LogCat.debug("be.bene.opa", "ObjectMaker", "configureOgoneOrder",
                "OPA " + order.getDossierNr(),
                "-> Order is an OgoneOrder, will configure it");

        OgoneAccount l_ogoneAccount = (OgoneAccount) l_ogoneOrder.getAccount();
        LogCat.debug(
                "be.bene.opa",
                "ObjectMaker",
                "configureOgoneOrder",
                "OPA " + order.getDossierNr(),
                "-> Will get the config. for this account and cast it to a OgoneAccountConfiguration");
        OgoneAccountConfig l_config = (OgoneAccountConfig) l_ogoneAccount.getConfiguration(m_accountConfigId);
        OgoneAccountConfig newConfig = (OgoneAccountConfig) l_config.clone();
        LogCat.debug("be.bene.opa", "ObjectMaker", "configureOgoneOrder", "OPA "
                + order.getDossierNr(), "---->>>   Config test: "
                + newConfig.getBgColorTag());

        LogCat.debug("be.bene.opa", "ObjectMaker", "configureOgoneOrder",
                "OPA " + order.getDossierNr(),
                "-> Will add this OgoneAccountConfiguration to the order");

        StringBuilder builder = new StringBuilder();
        String errorUrl = urlMaker(newConfig.getErrorUrl(), paymentNokUrl);
        newConfig.setErrorUrl(errorUrl);
        builder.append("error " + createLoggingUrl(errorUrl, newConfig.getErrorUrl(), paymentNokUrl));
        String failureUrl = urlMaker(newConfig.getFailureUrl(), paymentNokUrl);
        newConfig.setFailureUrl(failureUrl);
        builder.append("\nfailure " + createLoggingUrl(failureUrl, newConfig.getFailureUrl(), paymentNokUrl));
        String cancelUrl = urlMaker(newConfig.getCancelUrl(), paymentNokUrl);
        newConfig.setCancelUrl(cancelUrl);
        builder.append("\ncancel " + createLoggingUrl(cancelUrl, newConfig.getCancelUrl(), paymentNokUrl));
        String successUrl = urlMaker(newConfig.getSuccessUrl(), paymentOkUrl);
        newConfig.setSuccessUrl(successUrl);
        builder.append("\nsuccess " + createLoggingUrl(successUrl, newConfig.getSuccessUrl(), paymentOkUrl));
        LOGGER.info(builder.toString());

        String l_opaUrl4okNok = getOpaUrl4okNok();
        newConfig.setOpaSuccessUrl(l_opaUrl4okNok
                + "?BLName=PaymentOK&from=ogone&trx=" + order.getOrderID());
        newConfig.setOpaFailureUrl(l_opaUrl4okNok
                + "?BLName=PaymentNOK&from=ogone&trx=" + order.getOrderID());
        newConfig.setOpaErrorUrl(l_opaUrl4okNok
                + "?BLName=PaymentError&from=ogone&trx=" + order.getOrderID());

        l_ogoneOrder.setAccountConfig(newConfig);

        LogCat.debug("be.bene.opa", "ObjectMaker", "configureOgoneOrder",
                "OPA " + order.getDossierNr(),
                "-> OgoneOrder configuration done with accountConfig "
                        + newConfig);
    }

    private String createLoggingUrl(String resultUrl, String confUrl, String paymentUrl) {
        return "URL: " + resultUrl + "  -> conf URL: " + confUrl + "  app URL: " + paymentUrl;
    }

    private void configureDibsOrder(final DibsOrder dibsOrder) {
        LogCat.debug("be.bene.opa", "ObjectMaker", "configureDibsOrder",
                "OPA " + order.getDossierNr(), "-> Order is a DibsOrder, will configure it");

        DibsAccount dibsAccount = (DibsAccount) dibsOrder.getAccount();
        LogCat.debug("be.bene.opa", "ObjectMaker", "configureDibsOrder", "OPA " + order.getDossierNr(),
                "-> Will get the configuration for this account and cast it to a DibsAccountConfiguration");
        DibsAccountConfig l_config = (DibsAccountConfig) dibsAccount.getConfiguration(m_accountConfigId);
        DibsAccountConfig l_newConfig = (DibsAccountConfig) l_config.clone();
        LogCat.debug("be.bene.opa", "ObjectMaker", "configureDibsOrder", "OPA "
                + order.getDossierNr(), "---->>>   Config test: " + l_newConfig.getCancelUrl());

        l_newConfig.setCancelUrl(urlMaker(l_newConfig.getCancelUrl(), paymentNokUrl));
        l_newConfig.setSuccessUrl(urlMaker(l_newConfig.getSuccessUrl(), paymentOkUrl));
        String l_opaUrl4okNok = getOpaUrl4okNok();
        l_newConfig.setOpaSuccessUrl(l_opaUrl4okNok + "?BLName=PaymentOK&from=dibs&trx=" + order.getOrderID());
        l_newConfig.setOpaFailureUrl(l_opaUrl4okNok + "?BLName=PaymentNOK&from=dibs&trx=" + order.getOrderID());
        l_newConfig.setOpaErrorUrl(l_opaUrl4okNok + "?BLName=PaymentError&from=dibs&trx=" + order.getOrderID());
        dibsOrder.setAccountConfig(l_newConfig);
        LogCat.debug("be.bene.opa", "ObjectMaker", "configureDibsOrder",
                "OPA " + order.getDossierNr(), "-> Will add this DibsAccountConfiguration to the order");

        LogCat.debug("be.bene.opa", "ObjectMaker", "configureDibsOrder",
                "OPA " + order.getDossierNr(), "-> DibsOrder configuration done with accountConfig " + l_newConfig);
    }

    */
/*
     * Retrieve the OPA url for OK_NOK redirects by the PSP. This methods
     * ensures that the protocol will always be https.
     *//*

    private String getOpaUrl4okNok() {
        String l_opaUrl4okNok = OpaConfiguration.getInstance().getOpaUrlOkNokError();
        if (l_opaUrl4okNok == null) {
            LOGGER.error("-> opaUrl4okNok is null!!!");
        }

        if (l_opaUrl4okNok != null && l_opaUrl4okNok.lastIndexOf("https") < 0) {
            l_opaUrl4okNok = l_opaUrl4okNok.replaceAll("http:", "https:");
        }

        return l_opaUrl4okNok;
    }

    private void configureBibitOrder(BibitOrder l_bbOrder) {
        LogCat.debug("be.bene.opa", "ObjectMaker", "configureBibitOrder",
                "OPA " + order.getDossierNr(),
                "-> Order is BibitOrder, will configure it");

        BibitAccount l_bbAccount = (BibitAccount) l_bbOrder.getAccount();
        BibitAccountConfig l_config = (BibitAccountConfig) l_bbAccount.getConfiguration(m_accountConfigId);

        // Modified for habsys
        BibitAccountConfig l_newConfig = (BibitAccountConfig) l_config.clone();

        l_newConfig.setErrorUrl(urlMaker(l_newConfig.getErrorUrl(), paymentNokUrl));
        l_newConfig.setFailureUrl(urlMaker(l_newConfig.getFailureUrl(), paymentNokUrl));
        l_newConfig.setSuccessUrl(urlMaker(l_newConfig.getSuccessUrl(), paymentOkUrl));
        String l_opaUrl4okNok = getOpaUrl4okNok();
        l_newConfig.setOpaSuccessUrl(l_opaUrl4okNok
                + "?BLName=PaymentOK&from=bibit&trx=" + order.getOrderID());
        l_newConfig.setOpaFailureUrl(l_opaUrl4okNok
                + "?BLName=PaymentNOK&from=bibit&trx=" + order.getOrderID());
        l_newConfig.setOpaErrorUrl(l_opaUrl4okNok
                + "?BLName=PaymentError&from=bibit&trx=" + order.getOrderID());
        l_bbOrder.setAccountConfig(l_newConfig);

        if (m_hasShippingAddress) {
            l_bbOrder.setShippingAddress(m_shippingAddress);
        }
        LogCat.debug("be.bene.opa", "ObjectMaker", "configureBibitOrder",
                "OPA " + order.getDossierNr(),
                "-> BibitOrder configuration completed.");
    }

    */
/*
     * Parse the XML and build up the OPA objects
     *
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
     * java.lang.String, java.lang.String, org.xml.sax.Attributes)
     *//*

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attr) throws SAXException {
        contents.reset(); // empty buffer

        LogCat.debug("be.bene.opa", "ObjectMaker", "startElement", "OPA",
                "-> START ELEMENT");

        if (localName.equals("onlinePayment")) {
            String l_version = attr.getValue("version");
            m_applicationId = attr.getValue("applicationCode");
            if (VERSION_1_0.equals(l_version) && TESSY_VERSION_1_0_ID.equals(m_applicationId)) {
                LogCat.debug("be.bene.opa", "ObjectMaker", "startElement",
                        "OPA", "-> XML version " + l_version + ", sender is "
                                + m_applicationId
                                + ", will use default account "
                                + TESSY_DEF_ACCOUNT);
                m_accountId = TESSY_DEF_ACCOUNT;
            }
            LogCat.debug("be.bene.opa", "ObjectMaker", "startElement", "OPA",
                    "-> TAG onlinePayment: (attrib applicationId: "
                            + m_applicationId + " , attrib version: "
                            + l_version + ")");
        }

        if (localName.equals("clearingSystem")) {
            String l_clearingSystem = attr.getValue("name");
            LogCat.debug("be.bene.opa", "ObjectMaker", "startElement", "OPA",
                    "-> TAG clearingSystem " + l_clearingSystem);

            if (l_clearingSystem.equalsIgnoreCase("bibit")) {
                order = new BibitOrder();
                LogCat.debug("be.bene.opa", "ObjectMaker", "startElement",
                        "OPA", "-> Bibit order instantiated");
            }
            if (l_clearingSystem.equalsIgnoreCase("test")
                    || l_clearingSystem.equalsIgnoreCase("testRC")
                    || l_clearingSystem.equalsIgnoreCase("testPC")
                    || l_clearingSystem.equalsIgnoreCase("testNR")) {
                order = new TestOrder();
                LogCat.debug("be.bene.opa", "ObjectMaker", "startElement",
                        "OPA", "-> Test order instantiated");
            }
            if (l_clearingSystem.equalsIgnoreCase("ogone")) {
                order = new OgoneOrder();
                LogCat.debug("be.bene.opa", "ObjectMaker", "startElement",
                        "OPA", "-> Ogone order instantiated");
            }
            if (l_clearingSystem.equalsIgnoreCase("saferpay")) {
                order = new SaferPayOrder();
                LogCat.debug("be.bene.opa", "ObjectMaker", "startElement",
                        "OPA", "-> SaferPay order instantiated");
            }
            if (l_clearingSystem.equalsIgnoreCase("dibs")) {
                order = new DibsOrder();
                LogCat.debug("be.bene.opa", "ObjectMaker", "startElement",
                        "OPA", "-> DIBS order instantiated");
            }
        }

        if (localName.equals("account")) {
            LogCat.debug("be.bene.opa", "ObjectMaker", "startElement", "OPA",
                    "-> TAG account: ");
            m_accountId = attr.getValue("id");
            m_accountConfigId = attr.getValue("config");
            LogCat.debug("be.bene.opa", "ObjectMaker", "startElement", "OPA",
                    "-> AccountId: " + m_accountId + ", accountConfigId: "
                            + m_accountConfigId);
        }

        if (localName.equals("paymentMethod")) {
            LogCat.debug("be.bene.opa", "ObjectMaker", "startElement", "OPA",
                    "-> TAG paymentMethod: " + attr.getValue("type"));
            order.setPaymentMethod(attr.getValue("type"));
        }

        if (localName.equals("dossier")) {
            order.setDossierNr(attr.getValue("ident"));
            LogCat.debug("be.bene.opa", "ObjectMaker", "startElement", "OPA",
                    "-> TAG dossier: " + attr.getValue("ident"));
        }
        if (localName.equals("order")) {
            m_orderId = attr.getValue("transaction");
            if (m_orderId != null && !"".equals(m_orderId)) {
            }
        }
        if (localName.equals("clientInfo")) {
            order.setEMail(attr.getValue("mailAddress"));
            LogCat.debug("be.bene.opa", "ObjectMaker", "startElement", "OPA",
                    "-> TAG clientInfo: " + attr.getValue("mailAddress"));
        }

        if (localName.equals("amount")) {
            order.setCurrency(attr.getValue("currencyCode"));
            order.setExponent(attr.getValue("exponent"));
            LogCat.debug("be.bene.opa", "ObjectMaker", "startElement", "OPA",
                    "-> TAG amount: " + attr.getValue("currencyCode") + " "
                            + attr.getValue("exponent"));
        }

        if (localName.equals("customer")) {
            // create the customer for the order
            m_customer = new Customer();
            m_hasCustomer = true;
            LogCat.debug("be.bene.opa", "ObjectMaker", "startElement", "OPA",
                    "-> TAG customer: EXISTS");
        }

        if (localName.equals("deliveryAddress")) {
            // create the delivery address for this order
            m_shippingAddress = new DeliveryAddress();
            m_hasShippingAddress = true;
            LogCat.debug("be.bene.opa", "ObjectMaker", "startElement", "OPA",
                    "-> TAG deliveryAddress: EXISTS");
        }

        // Modified for habsys
        if (localName.equals("onlinePayment")) {
            paymentOkUrl = attr.getValue("applicationUrl");
            paymentNokUrl = attr.getValue("applicationUrl");
            if (org.apache.commons.lang.StringUtils.isNotBlank(attr.getValue("paymentOkUrl"))) {
                paymentOkUrl = attr.getValue("paymentOkUrl");
                okNokUrlOverride = true;
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(attr.getValue("paymentNokUrl"))) {
                paymentNokUrl = attr.getValue("paymentNokUrl");
                okNokUrlOverride = true;
            }
            LogCat.debug("be.bene.opa", "ObjectMaker", "startElement", "OPA",
                    "-> TAG applicationUrl: " + attr.getValue("applicationUrl"));
        }

        if (localName.equals("passengerName")) {
            PassengerName pasName = new PassengerName();
            pasName.setFirstName(attr.getValue("firstName"));
            pasName.setLastName(attr.getValue("lastName"));
            passengerNameList.add(pasName);
        }

        if (localName.equals("segmentParameters") && cur_segmentParameters == null) {
            cur_segmentParameters = new SegmentParameters();
        }
        if (localName.equals("origin") && cur_segmentParameters != null) {
            Station origin = new Station();
            origin.setShortCode(attr.getValue("shortCode"));
            origin.setLongCode(attr.getValue("longCode"));
            cur_segmentParameters.setOrigin(origin);
        }
        if (localName.equals("destination") && cur_segmentParameters != null) {
            Station destination = new Station();
            destination.setShortCode(attr.getValue("shortCode"));
            destination.setLongCode(attr.getValue("longCode"));
            cur_segmentParameters.setDestination(destination);
        }
        if (localName.equals("departureDate") && cur_segmentParameters != null) {
            String dateString = attr.getValue("value");
            if (dateString != null) {
                try {
                    cur_segmentParameters.setDepartureDate((new SimpleDateFormat("yyyy-MM-dd")).parse(dateString));
                } catch (Exception e) {
                    throw new SAXException("Error parsing departureDate", e);
                }
            }
        }
        if (localName.equals("distributor") && cur_segmentParameters != null) {
            cur_segmentParameters.setDistributor(attr.getValue("code"));
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if (localName.equals("segmentParameters") && cur_segmentParameters != null) {
            segmentParametersList.add(cur_segmentParameters);
            cur_segmentParameters = null;
        }

        if (localName.equals("customerId")) {
            order.setCustomerId(contents.toString());
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG customerId: " + contents.toString());
        }

        if (localName.equals("description")) {
            order.setDescription(contents.toString());
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG description: " + contents.toString());
        }

        if (localName.equals("amount")) {
            order.setAmount(contents.toString());
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG amount: " + contents.toString());
        }

        if (localName.equals("lang")) {
            order.setLang(contents.toString());
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG lang: " + contents.toString());
        }

        if (localName.equals("cc-owner-name")) {
            order.setNamePayer(StringUtils.stripAccents(contents.toString()));
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG cc-owner-name: " + contents.toString());
        }

        if (localName.equals("cc-use-3d-security")) {
            order.setUse3DSecurity(contents.toString());
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG cc-use-3d-security: " + contents.toString());
        }

        if (localName.equals("namePayer")) {
            if (!StringUtils.isValidString(order.getNamePayer())) {
                order.setNamePayer(StringUtils.stripAccents(contents.toString()));
                LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                        "-> TAG name-payer: " + contents.toString());
            }
        }

        if (localName.equals("pspTemplateUrl")) {
            order.setPspTemplateUrl(contents.toString());
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG pspTemplateUrl: " + contents.toString());
        }

        // Bibit specific tags - START
        if (localName.equals("viaStation")
                && this.getOrderType(order).equalsIgnoreCase("BibitOrder")) {
            ((BibitOrder) order).setViaStation(contents.toString());
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG viaStation: " + contents.toString());
        }

        if (localName.equals("travelerType")
                && this.getOrderType(order).equalsIgnoreCase("BibitOrder")) {
            ((BibitOrder) order).setTravelerType(contents.toString());
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG travelerType: " + contents.toString());
        }
        // Bibit specific tags - END

        if (localName.equals("firstName")) {
            m_customerName += contents.toString() + WHITESPACE;
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG firstName: " + contents.toString());
        }

        if (localName.equals("tussen")) {
            m_customerName += contents.toString() + WHITESPACE;
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG tussen: " + contents.toString());
        }

        if (localName.equals("lastName")) {
            m_customerName += contents.toString();
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG lastName: " + contents.toString());

        }

        if (localName.equals("street")) {
            m_customerAddress += contents.toString() + WHITESPACE;
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG street: " + contents.toString());
        }

        if (localName.equals("number")) {
            m_customerAddress += contents.toString() + WHITESPACE + "\n";
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG number: " + contents.toString());
        }

        if (localName.equals("zip")) {
            m_customerAddress += contents.toString() + WHITESPACE;
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG zip: " + contents.toString());
        }

        if (localName.equals("city")) {
            m_customerAddress += contents.toString() + "\n";
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG city: " + contents.toString());
        }

        if (localName.equals("country")) {
            m_customerAddress += contents.toString();
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG country: " + contents.toString());
        }
        if (localName.equals("email")) {
            m_customerEmail += contents.toString();
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG email: " + contents.toString());
        }

        if (localName.equals("dFirstName")) {
            m_shippingAddress.setFirstName(contents.toString());
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG dFirstName: " + contents.toString());
        }

        if (localName.equals("dMiddleName")) {
            m_shippingAddress.setMiddleName(contents.toString());
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG dMiddleName: " + contents.toString());
        }

        if (localName.equals("dLastName")) {
            m_shippingAddress.setLastName(contents.toString());
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG dLastName: " + contents.toString());
        }

        if (localName.equals("dstreet")) {
            m_shippingAddress.setStreet(contents.toString());
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG dstreet: " + contents.toString());
        }

        if (localName.equals("dnumber")) {
            m_shippingAddress.setNumber(contents.toString());
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG dnumber: " + contents.toString());
        }

        if (localName.equals("dbox")) {
            m_shippingAddress.setBox(contents.toString());
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG dbox: " + contents.toString());
        }

        if (localName.equals("dzip")) {
            m_shippingAddress.setZipcode(contents.toString());
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG dzip: " + contents.toString());
        }

        if (localName.equals("dcity")) {
            m_shippingAddress.setCity(contents.toString());
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG dcity: " + contents.toString());
        }

        if (localName.equals("dcountry")) {
            m_shippingAddress.setCountry(contents.toString());
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG dcountry: " + contents.toString());
        }

        if (localName.equals("clientSessionId")) {
            order.setClientSessionID(contents.toString());
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG clientSessionId: " + contents.toString());
        }

        if (localName.equals("alias")) {
            order.setAlias(contents.toString());
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG alias: " + contents.toString());
        }

        if (localName.equals("aliasUsage")) {
            order.setAliasUsage(contents.toString());
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG aliasUsage: " + contents.toString());
        }

        if (localName.equals("aliasOperation")) {
            order.setAliasOperation(contents.toString());
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG aliasOperation: " + contents.toString());
        }

        if (localName.equals("issuerId")) {
            order.setIssuerId(contents.toString());
            LogCat.debug("be.bene.opa", "ObjectMaker", "endElement", "OPA",
                    "-> TAG issuerId: " + contents.toString());
        }


    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        contents.write(ch, start, length);
    }

    //    private Map getApplications(HttpServlet p_servlet) {
    private Map<String, Application> getApplications() {
        // get the collection of applications from cache
        LogCat.debug("be.bene.opa", "ObjectMaker", "getApplications", "OPA",
                "-> Will retrieve all registered applications from cache (applicationscope)");

        if (applicationsConfiguration == null) {
            try {
                applicationsConfiguration = ApplicationsConfiguration.getInstance();
            } catch (ReloadException e) {
                LOGGER.error("Error loading applications", e);
            }
        }
        Map<String, Application> l_applications = applicationsConfiguration.getApplications();
        LogCat.debug("be.bene.opa", "ObjectMaker", "getApplications", "OPA",
                "-> All registered applications: " + l_applications.toString());
        return l_applications;
    }

    //    private Application getApplication(String p_applicationId,
//	    HttpServlet p_servlet) {
    private Application getApplication(String p_applicationId) {
        //	Application l_application = (Application) getApplications(p_servlet).get(
        Application l_application = getApplications().get(m_applicationId);
        if (l_application != null) {
			*/
/* debug *//*

            LogCat.debug("be.bene.opa", "ObjectMaker", "getApplication", "OPA",
                    "-> Found the cached application, will return it");
            return l_application;
        } // else
        LogCat.debug("be.bene.opa", "ObjectMaker", "getApplication", "OPA",
                "-> Could not find cached application " + p_applicationId + ", will return null");
        return m_application;
    }

    private String getOrderType(Order order) {
        String className = order.getClass().getName();
        int lastOcur = className.lastIndexOf(".");
        String orderType = className.substring(lastOcur != -1 ? lastOcur + 1
                : 0, className.length());
        return orderType;
    }

    private String urlMaker(String p_confURL, String p_appURL) {
        if (okNokUrlOverride) {
            return p_appURL;
        }
        String l_result = "";
        String l_dossier = "";

        if (order.getDossierNr() != null
                && !"".equals(order.getDossierNr())) {
            l_dossier = order.getDossierNr();
        }

        if (p_confURL != null && !"".equals(p_confURL)) {
            if (p_confURL.indexOf("_OKNOKURL_") >= 0) {
                if (p_appURL != null && !"".equals(p_appURL)) {
                    l_result = p_confURL.replaceAll("_OKNOKURL_", p_appURL);
                } else {
                    l_result = "ERROR! NO APP URL";
                    LOGGER.error("-> ERROR! no application URL in payment request");
                }
            } else {
                l_result = p_confURL;
            }
        } else {
            l_result = "ERROR! NO CONF URL";
            LOGGER.error("-> ERROR! no configuration URL in properties");
        }

        return l_result;
    }

    // Error handler to report errors and warnings
    private class MyErrorHandler implements ErrorHandler {
        */
/**
         * Error handler output goes here
         *//*

        private PrintStream out;

        MyErrorHandler(PrintStream out) {
            this.out = out;
        }

        */
/**
         * Returns a string describing parse exception details
         *//*

        private String getParseExceptionInfo(SAXParseException spe) {
            String systemId = spe.getSystemId();
            if (systemId == null) {
                systemId = "null";
            }
            String info = "URI=" + systemId + " Line=" + spe.getLineNumber()
                    + ": " + spe.getMessage();
            return info;
        }

        // The following methods are standard SAX ErrorHandler methods.
        // See SAX documentation for more info.

        public void warning(SAXParseException spe) throws SAXException {
            out.println("Warning: " + getParseExceptionInfo(spe));
        }

        public void error(SAXParseException spe) throws SAXException {
            String message = "Error: " + getParseExceptionInfo(spe);
            throw new SAXException(message);
        }

        public void fatalError(SAXParseException spe) throws SAXException {
            String message = "Fatal Error: " + getParseExceptionInfo(spe);
            throw new SAXException(message);
        }
    } // close MyErrorHandler class


    public String generateKey() throws InvalidKeyException {
        return KeyGenerator.getKey();
    }

    */
/**
     * Usefull to inject an ApplicationsConfiguration instance during testing.
     *
     * @param p_applicationsConfiguration the applicationsConfiguration to set
     *//*

    public void setApplicationsConfiguration(ApplicationsConfiguration p_applicationsConfiguration) {
        applicationsConfiguration = p_applicationsConfiguration;
    }
*/

}
