package com.github.jmetzz.laboratory.mockito.basic;

import com.github.jmetzz.laboratory.mockito.business.Printer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * Created by jean on 6/02/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderVerificationTest {

    @Mock
    private Printer printer;

    @Mock
    private List<String> list;

    @Test
    public void verify_in_order() {
        // Given
        InOrder inOrder = Mockito.inOrder(printer);

        // When
        printer.printTestPage();
        printer.turnOff();

        // Then
        inOrder.verify(printer).printTestPage();
        inOrder.verify(printer).turnOff();
    }


    @Test
    public void verify_in_order_fails() {
        // Given
        InOrder inOrder = Mockito.inOrder(printer);

        // When
        printer.turnOff();
        printer.printTestPage();

        // Then
        inOrder.verify(printer).printTestPage();
        inOrder.verify(printer).turnOff();
    }

    @Test
    public void verify_in_order_multiple() {
        // Given
        InOrder inOrder = Mockito.inOrder(printer, list);

        // When
        printer.printTestPage();
        list.clear();
        printer.turnOff();

        // Then
        inOrder.verify(printer).printTestPage();
        inOrder.verify(list).clear();
        inOrder.verify(printer).turnOff();
    }
}
