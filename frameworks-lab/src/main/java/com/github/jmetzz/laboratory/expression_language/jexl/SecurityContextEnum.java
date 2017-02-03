package com.github.jmetzz.laboratory.expression_language.jexl;

/**
 * Created by jean on 2/02/2017.
 */
public enum SecurityContextEnum {
    USER_ID("UserId", Integer.class),
    LEGAL_ENTITY_ID("legalEntityId", Integer.class),
    ORDER_REFERENCE("orderReference", Integer.class),
    COMMISSION_ASSIGNMENT_ID("commissionAssignmentId", Integer.class),
    COMMISSION_ASSIGNMENT_RULE_ID("commissionAssignmentRuleId", Integer.class),
    SUPPLIER_ID("supplierId", Integer.class),
    ACCOUNT_NUMBER("accountNumber", Integer.class),
    ACA_TYPE("acaType", Integer.class),
    ACA_CODE("acaCode", Integer.class),
    AMOUNT("amount", Integer.class);

    private String securityAttribute;
    private Class<?> classType;

    private SecurityContextEnum(String securityAttribute, Class<?> classType) {
        this.securityAttribute = securityAttribute;
        this.classType = classType;
    }

    public Class<?> getClassType() {
        return this.classType;
    }

    public String toString() {
        return this.securityAttribute;
    }

}
