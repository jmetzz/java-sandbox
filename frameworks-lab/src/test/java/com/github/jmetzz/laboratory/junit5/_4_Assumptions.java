package com.github.jmetzz.laboratory.junit5;


import org.junit.gen5.api.Test;

import java.io.File;


import static org.junit.gen5.api.Assumptions.*;
import static org.junit.gen5.api.Assertions.*;


/**
 * There are 3 static assumption methods: assumeTrue, assumeFalse and assumeThat.
 * These methods only represent assumption/conditions in which the test is useful.
 * When the assumptions are not met, meaning they fail, it not necessarily mean
 * the code not broken or not working properly, but rather the test case is not
 * providing useful information.
 * Therefore, The default JUnit runner treats tests with failing assumptions as ignored.
 * Custom runners may behave differently.
 *
 * For example, if a test fails when run in a different locale than the developer intended,
 * it can be fixed by explicitly passing a locale to the domain code.
 * sometimes this is not desirable or possible. It's good to be able to run a test against
 * the code as it is currently written, implicit assumptions and all, or to write a test
 * that exposes a known bug. For these situations, we can use assumptions.
 */
public class _4_Assumptions {



    @Test
    void testOnlyOnCiServer() {
        assumeTrue("CI".equals(System.getenv("ENV")));
        // remainder of test
    }

    @Test
    void testOnlyOnDeveloperWorkstation() {
        assumeTrue("DEV".equals(System.getenv("ENV")),
                () -> "Aborting test: not on developer workstation");
        // remainder of test
    }

    @Test
    void testInAllEnvironments() {
        assumingThat("CI".equals(System.getenv("ENV")),
                () -> {
                    // perform these assertions only on the CI server
                    assertEquals(2, 2);
                });

        // perform these assertions in all environments
        assertEquals("a string", "a string");
    }


}
