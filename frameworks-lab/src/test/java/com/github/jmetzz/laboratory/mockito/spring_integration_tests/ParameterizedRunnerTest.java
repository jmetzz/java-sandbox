package com.github.jmetzz.laboratory.mockito.spring_integration_tests;

import com.github.jmetzz.laboratory.mockito.business.Printer;
import com.github.jmetzz.laboratory.mockito.business.PrinterDiagnostics;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Created by jean on 6/02/2017.
 */
public class ParameterizedRunnerTest extends AbstractParameterizedBaseTest {

    @Autowired
    private Printer printer;

    @InjectMocks
    private PrinterDiagnostics diagnostics;

    @Captor
    private ArgumentCaptor<String> textCaptor;

    @Before
    public void setup() {
        Mockito.reset(printer);
    }

    @Test
    @Parameters(method = "parameters")
    public void verify_diagnostic_information_added_to_text(String text, Integer copies, Boolean collate) {
        // Given
        String expectedCopies = "Copies: " + copies;
        String expectedCollate = "Collate: " + collate;

        // When
        diagnostics.diagnosticPrint(text, copies, collate);

        // Then
        verify(printer).print(textCaptor.capture(), eq(copies), eq(collate));
        assertThat(textCaptor.getValue().contains(expectedCopies)).isTrue();
        assertThat(textCaptor.getValue().contains(expectedCollate)).isTrue();
        assertThat(textCaptor.getValue().contains(text)).isTrue();
    }


    private Object parameters() {
        return new Object[]{
                new String[]{"AAA", "2", "true"},
                new String[]{"AAA", "3", "false"},
                new String[]{"AAA", "1", "true"},
                new String[]{"AAA", "1", "false"}
        };
    }

}
