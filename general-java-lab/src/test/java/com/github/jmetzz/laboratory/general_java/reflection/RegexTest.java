package com.github.jmetzz.laboratory.general_java.reflection;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class RegexTest {



    @Test
    public void trivialPositiveTests(){
        assertThat("cab".replaceAll("(?<=a)b", "B")).isEqualTo("caB");
    }


    @Test
    public void trivialNegativeTests(){
        assertThat("debt".replaceAll("(?<!a)b", "B")).isEqualTo("deBt");
    }



	@Test
	@Parameters(method = "parameters")
	public void testRegex(String input, String expected) {
	    String actual = input
                .replaceAll("(?<=s)#", "s%")
                .replaceAll("(?<=(ss){0,100})#", "%") // (\\)*?\* -> $1*
                .replaceAll("(?<!ss)#", "%")
                .replaceAll("(?<=ss){0,100}s(?=#)", "s")
                .replaceAll("(?<=ss){0,100}s#", "s#") // -> match case 3: scaping operation but keep the slashes

//                .replaceAll("(?<=(?<!\\\\)\\\\)\\*", "*") // (\\)?\* -> *
                ;
//	    actual = actual.replaceAll("(?<=(\\\\\\\\){0,100}\\\\)\\*", "*"); // (\\)\* -> \*

        System.out.println(input + " => " + actual);
        assertThat(actual).isEqualTo(expected);
	}

	private Object parameters() {

        // map(#, *)
        // map(s, \\)
        return new String[][]{
                new String[] {"a", "a"},
                new String[] {"#", "%"},
                new String[] {"a#", "a%"},
                new String[] {"a#b", "a%b"},
                new String[] {"#b", "%b"},
                new String[] {"as#b", "as%b"},

				new String[] {"as#b", "a#b"},
				new String[] {"asss#b", "as#b"}
        };
    }

}
