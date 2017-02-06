package com.github.jmetzz.laboratory.mockito;

import com.github.jmetzz.laboratory.mockito.business.Printer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

/**
 * Created by jean on 6/02/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class TimeoutVerificationTest {

    @Mock
    private Printer printer;

    /* Mockito documentation states that:
     *  “It feels this feature should be used rarely – figure out a better way of testing your multi-threaded system.”
     * So take a deep breath and think about how to avoid this on your tests.
     */


    @Test
    public void verification_with_timeout() {
        // Given

        // When
        Executors.newFixedThreadPool(1).execute(() -> printer.printTestPage());

        // Then
        verify(printer, timeout(100)).printTestPage();
    }


    @Test
    public void verification_with_timeout_fails() throws InterruptedException {
        // Given

        // When
        Executors.newFixedThreadPool(1).execute(this::printTestWithSleep);

        // Then
        verify(printer, timeout(100)).printTestPage();
    }


    @Test
    public void verification_with_timeout_with_verification_mode() {
        // Given
        int poolsize = 5;

        // When
        ExecutorService service = Executors.newFixedThreadPool(poolsize);
        service.execute(this::printTestWithSleep);
        service.execute(this::printTestWithSleep);
        service.execute(this::printTestWithSleep);

        // Then
        verify(printer, timeout(500).times(3)).printTestPage();
    }

    @Test
    public void verification_with_timeout_with_verification_mode_fails() {
        // Given
        int poolsize = 1;

        // When
        ExecutorService service = Executors.newFixedThreadPool(poolsize);
        service.execute(this::printTestWithSleep);
        service.execute(this::printTestWithSleep);
        service.execute(this::printTestWithSleep);

        // Then
        verify(printer, timeout(500).times(3)).printTestPage();
    }


    private void printTestWithSleep() {
        try {
            Thread.sleep(200L);
            printer.printTestPage();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
