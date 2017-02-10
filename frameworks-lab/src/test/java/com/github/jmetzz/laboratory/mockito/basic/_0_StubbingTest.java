package com.github.jmetzz.laboratory.mockito.basic;

import com.github.jmetzz.laboratory.mockito.business.ConsolePrinter;
import com.github.jmetzz.laboratory.mockito.business.Printer;
import com.github.jmetzz.laboratory.mockito.business.StringProcessor;
import com.github.jmetzz.laboratory.mockito.exception.PrinterNotConnectedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

/**
 * Created by jean on 6/02/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class _0_StubbingTest {

    @Mock
    private Printer printer;

    @Spy
    private ConsolePrinter consolePrinter;

    @Test
    public void internal_buffer_should_be_absent_after_construction() {
        // Given
        StringProcessor processor = new StringProcessor(printer);

        // When
        Optional<String> actualBuffer = processor.statusAndTest();

        // Then
        assertFalse(actualBuffer.isPresent());
    }

    @Test
    public void test_page_should_be_printed_on_console() {
        // Given
        StringProcessor processor = new StringProcessor(consolePrinter);

        // When
        Optional<String> actualBuffer = processor.statusAndTest();

        // Then
        assertFalse(actualBuffer.isPresent());
    }

    @Test
    public void test_page_should_NOT_be_printed_on_console() {
        // Given
        StringProcessor processor = new StringProcessor(consolePrinter);

        // with partial mock or Spy we can stub the method that is called
        // to ensure that nothing happens in it using org.mockito.Mockito.doNothing()
        doNothing().when(consolePrinter).printTestPage();

        // When
        Optional<String> actualBuffer = processor.statusAndTest();

        // Then
        assertFalse(actualBuffer.isPresent());
    }

    @Test(expected = PrinterNotConnectedException.class)
    public void printer_not_connected_exception_should_be_thrown_up_the_stack() throws Exception {
        // Given
        StringProcessor processor = new StringProcessor(printer);
        doThrow(new PrinterNotConnectedException()).when(printer).printTestPage();

        // When
        Optional<String> actualBuffer = processor.statusAndTest();

        // Then
        assertFalse(actualBuffer.isPresent());
    }


}