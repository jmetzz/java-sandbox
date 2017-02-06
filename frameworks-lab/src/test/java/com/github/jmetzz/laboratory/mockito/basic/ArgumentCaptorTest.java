package com.github.jmetzz.laboratory.mockito.basic;

import com.github.jmetzz.laboratory.mockito.business.Printer;
import com.github.jmetzz.laboratory.mockito.business.PrinterDiagnostics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by jean on 6/02/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ArgumentCaptorTest {

    // instance of the class under test
    @InjectMocks
    private PrinterDiagnostics diagnostics;

    @Mock
    private Printer printer;

    //capture the text input to the printer
    @Captor //Mockito will automatically instantiate the ArgumentCaptor for us because we used the annotation
    private ArgumentCaptor<String> textCaptor;


    /*
     Now let’s create our test. We want to ensure two things:
     1.	The number of copies is added to the input text.
     2.	The state of the collate parameter is added to the input text.
     3.	The original text is maintained.
     */
    @Test
    public void verify_diagnostic_information_added_to_text() {
        // Given
        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";

        Integer copies = 3;
        Boolean collate = true;
        String expectedCopies = "Copies: " + copies;
        String expectedCollate = "Collate: " + collate;

        // When
        diagnostics.diagnosticPrint(text, copies, collate);

        // Then
        verify(printer).print(textCaptor.capture(), eq(copies), eq(collate));

        assertThat(textCaptor.getValue().contains(expectedCopies)).isTrue();
        assertThat(textCaptor.getValue().contains(expectedCollate)).isTrue();
        assertThat(textCaptor.getValue().contains(text)).isTrue();

        // check it out on the console
        System.out.println(textCaptor.getValue());
    }


    @Test
    public void verify_diagnostic_information_added_to_text_and_original_print() {
        // Given
        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        Integer copies = 3;
        Boolean collate = true;
        String expectedCopies = "Copies: " + copies;
        String expectedCollate = "Collate: " + collate;

        // When
        diagnostics.diagnosticAndOriginalPrint(text, copies, collate);

        // Then
        verify(printer, times(2)).print(textCaptor.capture(), eq(copies), eq(collate));
        List<String> texts = textCaptor.getAllValues();
        assertThat(texts.size()).isEqualTo(2);

        // First captured text is Diagnostic Print
        assertThat(texts.get(0).contains(expectedCopies)).isTrue();
        assertThat(texts.get(0).contains(expectedCollate)).isTrue();
        assertThat(texts.get(0).contains(text)).isTrue();

        // Second captured text is normal Print
        assertThat(texts.get(1).contains(expectedCopies)).isFalse();
        assertThat(texts.get(1).contains(expectedCollate));
        assertThat(text).isEqualTo(texts.get(1));
    }

}