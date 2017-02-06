package com.github.jmetzz.laboratory.mockito;

import com.github.jmetzz.laboratory.mockito.business.Printer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.atLeast;
import static org.mockito.internal.verification.VerificationModeFactory.atLeastOnce;
import static org.mockito.internal.verification.VerificationModeFactory.atMost;
import static org.mockito.internal.verification.VerificationModeFactory.only;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by jean on 6/02/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class InteractionsVerificationTest {

    @Mock
    private Printer printer;

    @Mock
    private List<String> list;

    @Test
    public void simple_interaction_verification() {
        // Given

        // When
        printer.printTestPage();

        // Then
        verify(printer).printTestPage();
    }

    @Test
    public void simple_interaction_verification_never() {
        // Given

        // When

        // Then
        verify(printer, never()).printTestPage();
    }


    @Test
    public void simple_interaction_verification_times_1() {
        // Given

        // When
        printer.printTestPage();

        // Then
        verify(printer, times(1)).printTestPage();
    }

    @Test
    public void simple_interaction_verification_times_3() {
        // Given

        // When
        printer.printTestPage();
        printer.printTestPage();
        printer.printTestPage();

        // Then
        verify(printer, times(3)).printTestPage();
    }

    @Test
    public void simple_interaction_verification_atLeastOnce() {
        // Given

        // When
        printer.printTestPage();
        printer.printTestPage();

        // Then
        verify(printer, atLeastOnce()).printTestPage();
    }

    @Test
    public void simple_interaction_verification_atLeast_2() {
        // Given

        // When
        printer.printTestPage();
        printer.printTestPage();
        printer.printTestPage();

        // Then
        verify(printer, atLeast(2)).printTestPage();
    }

    @Test
    public void simple_interaction_verification_atMost_3() {
        // Given

        // When
        printer.printTestPage();
        printer.printTestPage();
        printer.printTestPage();

        // Then
        verify(printer, atMost(3)).printTestPage();
    }


    // verify that the method being verified was the only method called on the mock .
    @Test
    public void simple_interaction_verification_only_interaction() {
        // Given

        // When
        printer.printTestPage();

        // Then
        verify(printer, only()).printTestPage();
    }

    @Test
    public void simple_interaction_verification_only_interaction_fails() {
        // Given

        // When
        printer.printTestPage();
        printer.turnOff();

        // Then
        verify(printer, only()).printTestPage();
    }

    @Test
    public void verify_zero_interactions() {
        //Given

        //When

        //Then
        verifyZeroInteractions(printer, list);
    }

    @Test
    public void verify_zero_interactions_fails() {
        //Given

        //When
        printer.printTestPage();

        //Then
        verifyZeroInteractions(printer, list);
    }

    @Test
    public void verify_no_more_interactions() {
        // Given
        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        Integer copies = 3;
        Boolean collate = true;

        // When
        printer.print(text, copies, collate);

        // Then
        verify(printer).print(text, copies, collate);
        verifyNoMoreInteractions(printer);
    }

    @Test
    public void verify_no_more_interactions_fails() {
        // Given
        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        Integer copies = 3;
        Boolean collate = true;

        // When
        printer.print(text, copies, collate);
        printer.turnOff();
        ;

        // Then
        verify(printer).print(text, copies, collate);
        verifyNoMoreInteractions(printer);
    }


}
