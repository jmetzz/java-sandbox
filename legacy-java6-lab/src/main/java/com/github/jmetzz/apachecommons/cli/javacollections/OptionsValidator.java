package com.github.jmetzz.apachecommons.cli.javacollections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OptionsValidator {

	
	static <K, V> boolean validate(Map<K, V> attrMap, Set<K> requiredAttrs, Set<K> permittedAttrs) {
	    boolean valid = true;
	    Set<K> attrs = attrMap.keySet();

	    if (! attrs.containsAll(requiredAttrs)) {
	        Set<K> missing = new HashSet<K>(requiredAttrs);
	        missing.removeAll(attrs);
	        System.out.println("Missing attributes: " + missing);
	        valid = false;
	    }
	    if (! permittedAttrs.containsAll(attrs)) {
	        Set<K> illegal = new HashSet<K>(attrs);
	        illegal.removeAll(permittedAttrs);
	        System.out.println("Illegal attributes: " + illegal);
	        valid = false;
	    }
	    return valid;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Set<String> requiredOptions = new HashSet<String>();
		requiredOptions.add("A");
		requiredOptions.add("B");
		requiredOptions.add("C");
		requiredOptions.add("D");
		
		Set<String> permittedOptions = new HashSet<String>(requiredOptions);
		permittedOptions.add("E");
		permittedOptions.add("F");
		permittedOptions.add("G");
		permittedOptions.add("H");
		
		Map<String, String> givenOptions = new HashMap<String, String>();
		givenOptions.put("A", "A value");
		givenOptions.put("B", "B value");
		givenOptions.put("C", "C value");
//		givenOptions.put("D", "d value");
		
		
		givenOptions.put("E", "E value");
//		givenOptions.put("F", "F value");
		givenOptions.put("G", "G value");
		givenOptions.put("H", "H value");
//		givenOptions.put("Invalid", "Invalid option");
		
		System.out.println(validate(givenOptions, requiredOptions, permittedOptions));
		
		
	}

}
