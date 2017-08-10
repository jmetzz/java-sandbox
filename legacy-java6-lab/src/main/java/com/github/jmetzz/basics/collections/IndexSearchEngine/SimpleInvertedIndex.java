package com.github.jmetzz.basics.collections.IndexSearchEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class SimpleInvertedIndex implements Index {

	private Map<String, Set<String>> mapFileToWords;
	private Map<String, Set<String>> mapWordToFiles; // inverted index
	private String path;

	public SimpleInvertedIndex(String path) {
		super();
		this.path = path;
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public String toString() {
		return "Database [path=" + path + "Files = " + mapFileToWords.size()
				+ "Words = " + mapWordToFiles.size() + "]";
	}

	// public void buildJ7() throws IOException {
	//
	// Path pathObj = Paths.get(this.path);
	//
	// if (!Files.isDirectory(pathObj, LinkOption.NOFOLLOW_LINKS))
	// throw new IOException("Missing directory: "
	// + this.getClass().getName());
	//
	// mapFileToWords = new HashMap<String, Set<String>>();
	//
	// /*
	// * TODO read the files list and
	// * load the words into the map
	// */
	// String[] fileList = Files.readAllLines(pathObj, );
	//
	// for (String f : fileList) {
	// mapFileToWords.put(f, loadWords(Paths.get(f)));
	// }
	// }
	//
	// private Set<String> loadWordsJ7(Path pathFile) throws IOException {
	//
	// if (Files.isReadable(pathFile)) {
	// Scanner scanner = new Scanner(pathFile);
	// Set<String> words = new TreeSet<String>();
	// while (scanner.hasNextLine()) {
	// String line = scanner.next();
	// words.addAll(Arrays.asList(line.split(" ")));
	// }
	// } else {
	// throw new IOException("Reading operation denied for file "
	// + pathFile.getFileName());
	// }
	// return null;
	// }
	
	@Override
	public void build() throws IOException {

		File dir = new File(this.path);
		if (!dir.isDirectory())
			throw new IOException("Missing directory: "
					+ this.getClass().getName());
		mapFileToWords = new HashMap<String, Set<String>>();
		mapWordToFiles = new HashMap<String, Set<String>>();
		File[] files = dir.listFiles();
		for (File f : files) {
			this.mapFileToWords.put(f.getName(),
					loadWordsJ6(f.getAbsolutePath()));
		}
		Iterator<String> docIt = mapFileToWords.keySet().iterator();
		while (docIt.hasNext()) {
			String key = docIt.next();
			register(key, mapFileToWords.get(key));
		}
	}

	private void register(String doc, Set<String> words) {

		for (String word : words) {
			if (!isIndexed(word)) {
				mapWordToFiles.put(word, new TreeSet<String>());
			}
			mapWordToFiles.get(word).add(doc);
		}
	}

	private boolean isIndexed(String word) {
		return (mapWordToFiles.get(word) != null);
	}

	private Set<String> loadWordsJ6(String fileName) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(fileName));
		Set<String> words = new TreeSet<String>();
		String line;
		while ((line = br.readLine()) != null) {
			for (String s : line.split(" "))
				words.addAll(Arrays.asList(clean(s).toLowerCase()));

		}
		return words;
	}

	private String clean(String line) {
		return (line.replaceAll("\\W", "_")).replaceAll("[0-9]", "");
	}

	@Override
	public Set<String> getWords(String file) {
		return mapFileToWords.get(file);
	}

	@Override
	public Set<String> executeQuery(String query) {
		return mapWordToFiles.get(query.toLowerCase());
	}

}
