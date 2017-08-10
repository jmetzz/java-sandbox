package com.github.jmetzz.functional.guava.functions;

import com.github.jmetzz.functional.guava.pojos.InventoryOrder;
import com.github.jmetzz.functional.guava.pojos.InventoryOrderState;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.jmetzz.functional.guava.pojos.InventoryOrderState.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by exi853 on 04/03/2016.
 */
public class FunctionsExampleTest {

    private static List<InventoryOrderState> ticketedStates;
    private static List<InventoryOrderState> notTicketedStates;

    @BeforeClass
    public static void setup(){
        ticketedStates = new ArrayList<InventoryOrderState>();

        ticketedStates.add(TICKETED);
        ticketedStates.add(TICKET_O_DNR);
        ticketedStates.add(C_TICKETLESS);
        ticketedStates.add(H_TICKETLESS);
        ticketedStates.add(S_TICKETLESS);

        notTicketedStates = new ArrayList<InventoryOrderState>();
        notTicketedStates.add(CREATED);
        notTicketedStates.add(CANCELLED);
        notTicketedStates.add(CONFIRMED);
        notTicketedStates.add(CONFIRMATION_IN_PROGRESS);
        notTicketedStates.add(BLOCKED);
        notTicketedStates.add(ON_HOLD);
        notTicketedStates.add(NONE);
        notTicketedStates.add(STORED);
    }


    @Test
    public void shouldBeTicketed(){
        List<InventoryOrder> list = new ArrayList<InventoryOrder>();
        assertThat(FunctionsExample.isTickted.apply(list)).isFalse();

        for(InventoryOrderState state: ticketedStates){
            list.add(new InventoryOrder(state));
            assertThat(FunctionsExample.isTickted.apply(list)).isTrue();
        }
    }

    @Test
    public void shouldNotBeTicketed(){
        List<InventoryOrder> list = new ArrayList<InventoryOrder>();
        assertThat(FunctionsExample.isTickted.apply(list)).isFalse();

        for(InventoryOrderState state: notTicketedStates){
            list.add(new InventoryOrder(state));
            assertThat(FunctionsExample.isTickted.apply(list)).isFalse();
        }

        list.clear();
        for(InventoryOrderState state: ticketedStates){
            list.add(new InventoryOrder(state));
        }
        // at this point it shold be ticketed.
        // so, let's try to make it not ticketed
        // by adding a non ticketed element

        for(InventoryOrderState state: notTicketedStates){
            list.add(new InventoryOrder(state));
            assertThat(FunctionsExample.isTickted.apply(list)).isFalse();
        }

    }

}