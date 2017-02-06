package com.github.jmetzz.laboratory.mockito;

import com.github.jmetzz.laboratory.mockito.model.Printer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by jean on 6/02/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ParameterVerificationTest {

    @Mock
    private Printer printer;

    @Test
    public void verification_with_actual_parameters() {
        // Given
        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        Integer copies = 3;
        Boolean collate = true;

        // When
        printer.print(text, copies, collate);

        // Then
        verify(printer).print(text, copies, collate);
    }

    @Test
    public void verification_with_actual_parameters_fails() {
        // Given
        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        String text2 = "Ut enim ad minim veniam, quis nostrud exercitation ullamco "
                + "laboris nisi ut aliquip ex ea commodo consequat.";
        Integer copies = 3;
        Boolean collate = true;

        // When
        printer.print(text2, copies, collate);

        // Then
        verify(printer).print(text, copies, collate);
    }

    @Test
    public void verification_with_actual_parameters_and_verification_mode() {
        // Given
        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        String text2 = "Ut enim ad minim veniam, quis nostrud exercitation ullamco "
                + "laboris nisi ut aliquip ex ea commodo consequat.";
        Integer copies = 3;
        Boolean collate = true;

        // When
        printer.print(text, copies, collate);

        // Then
        verify(printer, never()).print(text2, copies, collate);
    }

    @Test
    public void multiple_verification_with_actual_parameters() {
        // Given
        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        String text2 = "Ut enim ad minim veniam, quis nostrud exercitation ullamco "
                + "laboris nisi ut aliquip ex ea commodo consequat.";
        Integer copies = 3;
        Boolean collate = true;

        // When
        printer.print(text, copies, collate);
        printer.print(text2, copies, collate);

        // Then
        verify(printer).print(text, copies, collate);
        verify(printer).print(text2, copies, collate);
    }


    @Test
    public void verification_with_matchers() {
        // Given
        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        String text2 = "Ut enim ad minim veniam, quis nostrud exercitation ullamco "
                + "laboris nisi ut aliquip ex ea commodo consequat.";
        Integer copies3 = 3;
        Integer copies4 = 4;
        Boolean doCollate = true;
        Boolean doNotCollate = false;

        // When
        printer.print(text, copies3, doCollate);
        printer.print(text2, copies4, doNotCollate);

        // Then
        verify(printer, times(2)).print(anyString(), anyInt(), anyBoolean());
    }

    @Test
    public void verification_with_mixed_matchers() {
        // Given
        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        String text2 = "Ut enim ad minim veniam, quis nostrud exercitation ullamco "
                + "laboris nisi ut aliquip ex ea commodo consequat.";
        Integer copies = 5;
        Boolean collate = true;

        // When
        printer.print(text, copies, collate);
        printer.print(text2, copies, collate);

        // Then
        verify(printer, times(2)).print(anyString(), eq(copies), eq(collate));
    }

    @Test
    public void verification_with_mixed_matchers_fails() {
        // Given
        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        Integer copies5 = 5;
        Integer copies10 = 10;
        Boolean collate = true;

        // When
        printer.print(text, copies10, collate);

        // Then
        verify(printer).print(anyString(), eq(copies5), eq(collate));
    }
}
