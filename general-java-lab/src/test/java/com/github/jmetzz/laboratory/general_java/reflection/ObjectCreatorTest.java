package com.github.jmetzz.laboratory.general_java.reflection;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

/**
 * Created by jean on 23/03/2017.
 */
@RunWith(JUnitParamsRunner.class)
public class ObjectCreatorTest {

	@Test
	@Parameters(method = "validParameters")
	public void shouldCreateObject(String type, String value)
			throws ClassNotFoundException, NoSuchMethodException, IllegalArgumentException {
		ObjectCreator creator = new ObjectCreator();
		// When
		Object o = creator.create(type, value);
		assertThat(o).isNotNull();
	}

	@Test(expected = ClassNotFoundException.class)
	@Parameters(method = "notValidTypes")
	public void shouldFireExceptionDueToInvalidType(String type, String value)
			throws ClassNotFoundException, NoSuchMethodException, IllegalArgumentException {
		ObjectCreator creator = new ObjectCreator();
		// When
		Object o = creator.create(type, value);
		assertThat(o).isNotNull();
	}

	@Test(expected = IllegalArgumentException.class)
	@Parameters(method = "notValidValues")
	public void shouldFireExceptionDueToInvalidValue(String type, String value)
			throws ClassNotFoundException, NoSuchMethodException, IllegalArgumentException {
		ObjectCreator creator = new ObjectCreator();
		// When
		Object o = creator.create(type, value);
		assertThat(o).isNotNull();
	}

	private Object validParameters() {
		return new Object[] {
				new String[] { "java.lang.Integer", "5" },
				new String[] { "java.lang.Double", "5.3" },
				new String[] { "java.lang.Float", "5.5" },
				new String[] { "java.lang.Short", "5" },
				new String[] { "java.lang.Boolean", "true" },
				new String[] { "java.lang.Long", "55" }
		};
	}

	private Object notValidTypes() {
		return new Object[] {
				new String[] { "java.lang.BooleanX", "truuuu" },
				new String[] { "java.lang.ShortX", "5,55a" },
				new String[] { "java.lang.IntegerX", "10,5a" },
				new String[] { "java.lang.LongX", "10000a,12" },
				new String[] { "java.lang.FloatX", "5.5a" },
				new String[] { "java.lang.DoubleX", "6.6a" }
		};
	}

	private Object notValidValues() {
		return new Object[] {
				new String[] { "java.lang.Boolean", "truuuu" },
				new String[] { "java.lang.Short", "5,55a" },
				new String[] { "java.lang.Short", "55555555555555" },
				new String[] { "java.lang.Integer", "10,5a" },
				new String[] { "java.lang.Long", "10000a,12" },
				new String[] { "java.lang.Float", "5.5a" },
				new String[] { "java.lang.Double", "6.6a" }
		};
	}

}