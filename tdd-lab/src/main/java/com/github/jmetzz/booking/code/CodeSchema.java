package com.github.jmetzz.booking.code;

public enum CodeSchema {
    BDWS,
    TACO,
    ITTI,
    EPA,
    BIL,
    HAF,
    NVS,
    AMA,
    NOT_SUPPORTED;

    private CodeSchema() {
    }

    public static CodeSchema safeValueOf(String p_value) {
        try {
            return valueOf(p_value);
        } catch (RuntimeException var2) {
            return NOT_SUPPORTED;
        }
    }
}
