package com.github.jmetzz.apachecommons.cli.propertiesFiles;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public final class Configuration {
	/**
	 * The property key under which the config file name is stored.
	 */
	public static final String CONFIG_FILE_KEY = "parseOptions.config";

	/**
	 * The default configuration file name.
	 */
	private static final String DEFAULT_CONFIG_FILE = "resources/parseOptions.properties";

	/**
	 * The message (help) file name.
	 */
	private static final String MESSAGE_FILE = "resources/parseOptions.messages";

	/**
	 * The log configuration file.
	 */
	private static final String LOG_FILE = "resources/logging.properties";

	// Initializes the logging configuration
	static {
		InputStream configuration = Configuration.class.getResourceAsStream(LOG_FILE);

		if (configuration == null) {
			// turns off logging
			LogManager.getLogManager().getLogger("").setLevel(Level.OFF);
		} else {
			try {
				LogManager.getLogManager().readConfiguration(configuration);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * The singleton configuration instance.
	 */
	private static final Configuration singleton = new Configuration();

	/**
	 * The runtime properties.
	 */
	private Properties properties;

	/**
	 * Default private constructor.
	 */
	private Configuration() {
		String name = System.getProperty(CONFIG_FILE_KEY, 
				DEFAULT_CONFIG_FILE);

		if (!name.startsWith("/")) {
			name = "/" + name;
		}

		properties = new Properties();

		try {
			properties.load(Configuration.class.getResourceAsStream(name));
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).warning(
					"Error reading configuration file: " + name);
		}
	}

	/**
	 * Returns the singleton instance.
	 * 
	 * @return the singleton instance.
	 */
	private static Configuration getInstance() {
		return singleton;
	}

	/**
	 * Returns the property indicated by the specified key.
	 * 
	 * @param key
	 *            the name of the system property.
	 * 
	 * @return the property indicated by the specified key, or <code>null</code>
	 *         if there is no property with that key.
	 */
	public static String getProperty(String key) {
		// system property overrides the configuration file
		String value = System.getProperty(key);

		if (value == null) {
			return getInstance().properties.getProperty(key);
		}

		return value;
	}

	/**
	 * Returns the property indicated by the specified key as an integer value.
	 * 
	 * @param key
	 *            the name of the system property.
	 * 
	 * @return the property indicated by the specified key, or <code>null</code>
	 *         if there is no property with that key.
	 */
	public static int getIntProperty(String key) {
		return Integer.parseInt(getProperty(key));
	}

	/**
	 * Returns the property indicated by the specified key as an long value.
	 * 
	 * @param key
	 *            the name of the system property.
	 * 
	 * @return the property indicated by the specified key, or <code>null</code>
	 *         if there is no property with that key.
	 */
	public static long getLongProperty(String key) {
		return Long.parseLong(getProperty(key));
	}

	/**
	 * Returns the property indicated by the specified key as a double value.
	 * 
	 * @param key
	 *            the name of the system property.
	 * 
	 * @return the property indicated by the specified key.
	 */
	public static double getDoubleProperty(String key) {
		return Double.parseDouble(getProperty(key));
	}

	/**
	 * Returns the property indicated by the specified key as a boolean value.
	 * 
	 * @param key
	 *            the name of the system property.
	 * 
	 * @return the property indicated by the specified key.
	 */
	public static boolean getBooleanProperty(String key) {
		return Boolean.parseBoolean(getProperty(key));
	}

	/**
	 * Returns the property indicated by the specified key.
	 * 
	 * @param key
	 *            the name of the system property.
	 * @param defaultValue
	 *            a default value.
	 * 
	 * @return the property indicated by the specified key, or the default value
	 *         if there is no property with that key.
	 */
	public static String getProperty(String key, String defaultValue) {
		String value = getProperty(key);
		return (value == null ? defaultValue : value);
	}

	/**
	 * Returns the property indicated by the specified key as an integer value.
	 * 
	 * @param key
	 *            the name of the system property.
	 * @param defaultValue
	 *            a default value.
	 * 
	 * @return the property indicated by the specified key, or the default value
	 *         if there is no property with that key.
	 */
	public static int getIntProperty(String key, int defaultValue) {
		return Integer
				.parseInt(getProperty(key, Integer.toString(defaultValue)));
	}

	/**
	 * Returns the property indicated by the specified key as an long value.
	 * 
	 * @param key
	 *            the name of the system property.
	 * @param defaultValue
	 *            a default value.
	 * 
	 * @return the property indicated by the specified key, or the default value
	 *         if there is no property with that key.
	 */
	public static long getLongProperty(String key, int defaultValue) {
		return Long.parseLong(getProperty(key, Long.toString(defaultValue)));
	}

	/**
	 * Returns the property indicated by the specified key as an double value.
	 * 
	 * @param key
	 *            the name of the system property.
	 * @param defaultValue
	 *            a default value.
	 * 
	 * @return the property indicated by the specified key, or the default value
	 *         if there is no property with that key.
	 */
	public static double getDoubleProperty(String key, double defaultValue) {
		return Double.parseDouble(getProperty(key,
				Double.toString(defaultValue)));
	}

	/**
	 * Returns the property indicated by the specified key as a boolean value.
	 * 
	 * @param key
	 *            the name of the system property.
	 * @param defaultValue
	 *            a default value.
	 * 
	 * @return the property indicated by the specified key, or the default value
	 *         if there is no property with that key.
	 */
	public static boolean getBooleanProperty(String key, boolean defaultValue) {
		return Boolean.parseBoolean(getProperty(key,
				Boolean.toString(defaultValue)));
	}

	/**
	 * Checks if the property has a value associated.
	 * 
	 * @param key
	 *            the property key.
	 * 
	 * @return <code>true</code> if the property has a value associated;
	 *         otherwise <code>false</code>.
	 * 
	 * @see #getProperty(String)
	 */
	public static boolean isPresent(String key) {
		return getProperty(key) != null;
	}

	/**
	 * Returns the configuration property options.
	 * 
	 * @return the configuration property options.
	 */
	public static Properties getOptions() {
		try {
			Properties messages = new Properties();
			String aux; 
			if (!MESSAGE_FILE.startsWith("/")) {
				aux = "/" + MESSAGE_FILE;
			} else 
				aux = MESSAGE_FILE;
			
			messages.load(Configuration.class.getResourceAsStream(aux));

			return messages;
		} catch (Exception e) {
			throw new RuntimeException("Messages file not found: "
					+ MESSAGE_FILE);
		}
	}

}