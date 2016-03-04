package com.github.jmetzz.functional.guava.pojos;

public enum InventoryOrderState {

    CREATED("created"),
    STORED("stored"),
    CONFIRMED("confirmed"),
    TICKETED("ticketed"),
    CANCELLED("cancelled"),
    ON_HOLD("on_hold"),
    NONE("none"),
    C_TICKETLESS("c_ticketless"),
    S_TICKETLESS("s_ticketless"),
    H_TICKETLESS("h_ticketless"),
    TICKET_O_DNR("ticket_o_dnr"),
    BLOCKED("blocked"),
    CONFIRMATION_IN_PROGRESS("confirmation_in_progress");

    private final String value;

    private InventoryOrderState(String v) {
        this.value = v;
    }

    public String value() {
        return this.value;
    }

    public static InventoryOrderState fromValue(String v) {
        InventoryOrderState[] arr$ = values();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            InventoryOrderState c = arr$[i$];
            if(c.value.equals(v)) {
                return c;
            }
        }

        throw new IllegalArgumentException(v);
    }
}
