package com.github.jmetzz.laboratory.general_java.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Created by jean on 23/03/2017.
 */
public class ObjectCreator {

	public Object create(String type, String value) throws ClassNotFoundException, NoSuchMethodException, IllegalArgumentException {

		Class c = Class.forName(type);

		if (c.isAssignableFrom(String.class)) {
			return value;
		} else if (c.isAssignableFrom(Boolean.class)) {
			if( !value.equalsIgnoreCase("true") && !value.equalsIgnoreCase("false") )
				throw new IllegalArgumentException("Wrong input value");
			return Boolean.getBoolean(value);
		} else {
			Method m = c.getMethod("valueOf", new Class[] { String.class });
			try {
				return m.invoke(c, value);
			} catch (IllegalAccessException | InvocationTargetException e) {
				throw new IllegalArgumentException("Wrong input value");
			}
		}
	}
}
