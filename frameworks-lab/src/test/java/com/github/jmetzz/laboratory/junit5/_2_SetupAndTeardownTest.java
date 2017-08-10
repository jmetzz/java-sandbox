package com.github.jmetzz.laboratory.junit5;

import org.junit.gen5.api.*;

import static org.junit.gen5.api.Assertions.assertTrue;
import static org.junit.gen5.api.Assertions.fail;

/**
 * Now with JUnit generation 5 test classes don't
 * have to be public.
 */

/* you can define names for test class or methods, including spaces and emojis */
@DisplayName("My cool name and face :)")
/* @Tag ans @Tags are used to categorized test cases, they are the equivalent to Categories in JUnit4.
 * You can filter test execution based on tag names configuration to the test runners
 * */
@Tag("high-priority-test")
@Tags({
        @Tag("high-priority-test"),
        @Tag("tutorial-test")
})
class _2_SetupAndTeardownTest {


    @BeforeAll // this is equivalent to @BeforeClass in JUnit4
    public void globalSetup(){
        System.out.println("Configuring global behaviour");
    }

    @BeforeEach // this is equivalent to @Before in JUnit4
    public void stepSetup(){
        System.out.println("Configuring step/testcase behaviour");
    }

    @AfterAll // this is equivalent to @AfterClass in JUnit4
    public void globalTeardown(){
        System.out.println("Global clean up");
    }

    @AfterEach // this is equivalent to @After in JUnit4
    public void stepTeardown(){
        System.out.println("Step clean up");
    }


    /* @Test annotations, as well as other annotations, were moved to
    * a new package. See the import statement.
    * */
    @Test
    @DisplayName("My single enabled test method")
    @Tag("quick-test")
    public void shouldPassSimpleTest(){
        assertTrue(true);
    }

    @Test
    @Disabled // this annotation is analogous to to @Ignore of JUnit4
    @DisplayName("My disabled test method")
    public void willNotBeTested(){
        fail("Test method not implemented yet");
    }

}
