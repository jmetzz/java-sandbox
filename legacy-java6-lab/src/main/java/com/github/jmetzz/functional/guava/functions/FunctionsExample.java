package com.github.jmetzz.functional.guava.functions;

import com.github.jmetzz.functional.guava.pojos.InventoryOrder;
import com.github.jmetzz.functional.guava.pojos.InventoryOrderState;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;

import java.util.List;

import static com.google.common.base.Predicates.not;

/**
 * Created by exi853 on 04/03/2016.
 */
public class FunctionsExample {

    public static Function<List<InventoryOrder>, Boolean> isTickted = new Function<List<InventoryOrder>, Boolean>() {
        @Override
        public Boolean apply(List<InventoryOrder> orderList) {
            return orderList.isEmpty() ? false : FluentIterable.from(orderList).filter(not(ticketPredicate)).isEmpty();
        }
    };

    public static Predicate<InventoryOrder> ticketPredicate = new Predicate<InventoryOrder>() {
        @Override
        public boolean apply(InventoryOrder inventoryOrder) {

            InventoryOrderState inventoryOrderState = inventoryOrder.getInventoryOrderState();
            if ((!inventoryOrderState.equals(InventoryOrderState.TICKETED)
                    && !inventoryOrderState.equals(InventoryOrderState.TICKET_O_DNR)
                    && !inventoryOrderState.equals(InventoryOrderState.C_TICKETLESS)
                    && !inventoryOrderState.equals(InventoryOrderState.H_TICKETLESS)
                    && !inventoryOrderState.equals(InventoryOrderState.S_TICKETLESS))
                    ) {
                return false;
            }
            return true;
        }
    };
}
