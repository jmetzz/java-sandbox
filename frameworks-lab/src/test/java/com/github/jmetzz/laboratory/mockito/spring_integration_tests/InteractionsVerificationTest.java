package com.github.jmetzz.laboratory.mockito.spring_integration_tests;

import com.github.jmetzz.laboratory.mockito.business.Printer;
import com.github.jmetzz.laboratory.mockito.spring_integration_tests.config.TestApplicationContext;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by jean on 6/02/2017.
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {TestApplicationContext.class})
public class InteractionsVerificationTest {

    /*
    SpringClassRule is a custom JUnit TestRule that supports class-level features
    of the Spring TestContext Framework in standard JUnit tests by means of the
    TestContextManager and associated support classes and annotations.
    In contrast to the SpringJUnit4ClassRunner, Spring's rule-based JUnit support
    has the advantage that it is independent of any Runner and can therefore
    be combined with existing alternative runners like JUnit's Parameterized or
    third-party runners such as the MockitoJUnitRunner.

    In order to achieve the same functionality as the SpringJUnit4ClassRunner, however,
    a SpringClassRule must be combined with a SpringMethodRule,
    since SpringClassRule only supports the class-level features of the SpringJUnit4ClassRunner.
    */


    // enable the following annotations @BeforeClass, @AfterClass, @ProfileValueSourceConfiguration, @IfProfileValue,
    // which are class-level features of the SpringJUnit4ClassRunner
    // The name of the field doesn’t matter but it must be public, static and final
    @ClassRule
    public static final SpringClassRule springClassRule = new SpringClassRule();

    // enable the following annotations @Before, @After, @Repeat, @Timeout, @ProfileValueSourceConfiguration, @IfProfileValue,
    // which are instance-level and method-level features of the SpringJUnit4ClassRunner
    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private Printer printer;


    @Test
    public void simple_interaction_verification_times_1() {
        // Given

        // When
        printer.printTestPage();

        // Then
        verify(printer, times(1)).printTestPage();
    }


    /*
    This test fails because the interactions are not cleared up between test cases.
    Since 'printer' is a spring bean, mockito doesn't reset its counter :(
    Thus, this is one of the few scenarios where you should use Mockito.reset() method
    to make some clean up between test cases. Use it with care to avoid even more problems.
    See the solution on @link{com.github.jmetzz.laboratory.mockito.spring_integration_tests.ResetInteractionsVerificationTest}
     */
    @Test
    public void simple_interaction_verification_times_3_fails() {
        // Given

        // When
        printer.printTestPage();
        printer.printTestPage();
        printer.printTestPage();

        // Then
        verify(printer, times(3)).printTestPage();
    }




}
