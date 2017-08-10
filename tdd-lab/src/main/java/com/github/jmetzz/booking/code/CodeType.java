package com.github.jmetzz.booking.code;


public enum CodeType {

    ACCOMMODATION_TYPE,
    ANY_STATION_ZONE,
    CLASS,
    CLASS_OF_SERVICE,
    JOURNEY_TYPE,
    REDUCTION_CARD,
    LANGUAGE,
    UNKNOWN;

    public static CodeType safeValueOf(String p_value) {
        try {
            return valueOf(p_value);
        } catch (RuntimeException var2) {
            return UNKNOWN;
        }
    }
}
