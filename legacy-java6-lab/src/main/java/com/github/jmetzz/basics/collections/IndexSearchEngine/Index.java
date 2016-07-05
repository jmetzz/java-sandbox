package com.github.jmetzz.basics.collections.IndexSearchEngine;

import java.io.IOException;
import java.util.Set;

public interface Index {
	 
	/**
	 * Builds the index structure
	 * @throws IOException
	 */
	public void build() throws IOException;
	/**
	 * Perform the query based on the specified string
	 * @param query a String used to search 
	 * @return the list of strings associated to the query parameter
	 */
	public Set<String> executeQuery(String query);
	/**
	 * Retrieves the indexed set of words
	 * @param file the name of the reference file 
	 * @return a set of Strings containing all indexed words
	 */
	public Set<String> getWords(String file);
	/**
	 * Get the path where the source files are located
	 * @return the path
	 */
	public String getPath();
}
