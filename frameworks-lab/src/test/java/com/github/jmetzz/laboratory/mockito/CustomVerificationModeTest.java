package com.github.jmetzz.laboratory.mockito;

import com.github.jmetzz.laboratory.mockito.business.Printer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.github.jmetzz.laboratory.mockito.First.first;
import static org.mockito.Mockito.verify;

/**
 * Created by jean on 6/02/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomVerificationModeTest {

    @Mock
    private Printer printer;

    @Test
    public void simpleInteraction_CustomVerification_First(){
        //Given

        //When
        printer.printTestPage();
        printer.turnOff();

        //Then
        verify(printer, first()).printTestPage();
    }

    @Test
    public void simpleInteraction_CustomVerification_First_fails(){
        //Given

        //When
        printer.turnOff();
        printer.printTestPage();

        //Then
        verify(printer, first()).printTestPage();
    }

}
