package com.github.jmetzz.laboratory.concurrency.standalone.oauthTokenRefreshExample.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

public class PropertyLoader {
	/**
	 * Stream to be read from
	 */
	private static InputStream input = null;
	/**
	 * Name of the properties file
	 */
	private static final String PROPERTIES_FILENAME = "concurrency-lab/src/main/resources/custompip.properties";
	/**
	 * Location to store the property file.
	 */
	private static final String PROPERTIES_LOCATION = "";// Thread.currentThread().getContextClassLoader().getResource("oauth/util/").getPath();
	/**
	 * File representation of the property file.
	 */
	private static File propFile;
	
	private static CompositeConfiguration config;
	
	private static final Logger LOGGER = Logger.getLogger( PropertyLoader.class.getName() );
	
	/**
	 * Hotfix for disabling the instance creation of this class.
	 */
	private PropertyLoader(){		
	}
	
	static {
		if(checkPropertiesFile()){
			loadProperties();		
			printProperties();
			System.out.println("Properties loaded!");
		}
	}
	
	public static void printProperties(){
		Iterator<String> keys = config.getKeys();
		SortedSet<String> sortedKeys = new TreeSet<String>();
		while (keys.hasNext()) {
			sortedKeys.add(keys.next());
		}
		for (String key : sortedKeys) {
			System.out.println(key + " = " + config.getString(key));
		}
	}
	
	private static void loadProperties() {
		CompositeConfiguration newConfig = new CompositeConfiguration();
		try {
			// load properties from System properties first
			newConfig.addConfiguration(new SystemConfiguration());
			if (propFile.exists()) {
				PropertiesConfiguration pc = new PropertiesConfiguration(propFile.getAbsolutePath());
				FileChangedReloadingStrategy frs = new FileChangedReloadingStrategy();
				frs.setRefreshDelay(60 * 1000);
				pc.setReloadingStrategy(frs);
				newConfig.addConfiguration(pc);
			}

			// switch to new configuration and cleanup old configuration
			CompositeConfiguration oldConfig = config;
			config = newConfig;
			if (oldConfig!=null) oldConfig.clear();
		} catch (ConfigurationException e) {
			e.printStackTrace(System.err);
		}
	}
	
	
	/**
	 * @param key	Key of the property value
	 * @return Boolean representing existance of the key
	 */
	public static Boolean propertyExists(String key){
		Boolean exists = false;		
		checkPropertiesFile();
		Properties prop = new Properties();
		loadInputStream();
		try {
			prop.load(input);
			exists = prop.containsKey(key);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return exists;
	}
	
	static Properties getProperties(){
		checkPropertiesFile();
		Properties prop = new Properties();
		loadInputStream();
		try {
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
	
	/**
	 * Gets a property for a given key from the properties file
	 * @param key Key of the property value
	 * @return value for the given key
	 */
	public static String getPropertyOld(String key){
		checkPropertiesFile();
		String result = "N/A";
		Properties prop = new Properties();
		loadInputStream();
		try {
			prop.load(input);
			result = prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public static String getProperty(String key){
		return config.getString(key);
	}
	
	/**
	 * Loads the local inputstream
	 */
	private static void loadInputStream() {		
		try {
			input = new FileInputStream(propFile);
		} catch (FileNotFoundException e) {
			LOGGER.log(Level.WARNING,e.getMessage());
		}
	}
	
	/**
	 * This method searches for a properties file. If no file can be found creates one with default settings.
	 * @return if true, a properties file has been found. If false, a property file with default options used in the oam.localdomain/iis11g.localdomain lab is created
	 */
	private static boolean checkPropertiesFile(){
		propFile = new File(PROPERTIES_LOCATION + PROPERTIES_FILENAME);
		LOGGER.log(Level.INFO, "Checking property file on: " + propFile.getAbsolutePath());
		boolean exists = false;
		if (propFile.isFile()){
			LOGGER.log(Level.INFO, "Found property file");
			exists = true;
		} else {
			LOGGER.log(Level.SEVERE,"=====================================================");
			LOGGER.log(Level.SEVERE,"            ERROR NO PROPERTIES FILE FOUND");
			LOGGER.log(Level.SEVERE,"     PLACE PROPERTIES FILE IN THE LOCATION BELOW");
			LOGGER.log(Level.SEVERE,propFile.getAbsolutePath());
			LOGGER.log(Level.SEVERE,"=====================================================");
			//LOGGER.log(Level.INFO,"Creating default properties file!\nFile will be placed at: " + propFile.getAbsolutePath());
			//loadDefaultProperties();
		}
		
		return exists;
	}
}
