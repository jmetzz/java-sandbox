package com.github.jmetzz.laboratory.general_java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class VerySimpleTests {

	public static void main(String[] args) {


		ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

		ImmutableList<Integer> list = FluentIterable.from(integers).filter(new Predicate<Integer>() {
			@Override
			public boolean apply(Integer integer) {
				return integer > 6;
			}
		})
				.filter(new Predicate<Integer>() {
					@Override
					public boolean apply(Integer integer) {
						return integer < 10;
					}
				})
				.toList();

		System.out.println(list);

		System.out.println(Optional.fromNullable(null));

//		Boolean b = null;
//
//		if (!b){
//			System.out.println("null");
//		}


//		parseHttpResponseBodyToString();

		// SortedSet<String> set = new TreeSet<>();
		//
		// set.add("a");
		// set.add("ab");
		// set.add("aa");
		// set.add("aba");
		//
		//
		// System.out.println(set);
		// System.out.println(Collections.binarySearch(new ArrayList<>(set), "aa"));

		// List<Integer> list = Arrays.asList(1,43,5,5,5,5,6,7,2,2,2,2,2, 2, 2);
		// Collections.sort(list);
		// System.out.println(list);
		//
		// int index = Collections.binarySearch(list, 2);
		// System.out.println(index);
		//
		// index = Collections.binarySearch(list, 3);
		// System.out.println(index);

		// List<String> fragments = Arrays.asList("search", "search,UI", "orderForm,UI,0551", "orderForm,01",
		// "orderForm,01AX");
		// System.out.println(fragments);
		// testRegex(fragments);

		// for (String f : fragments) {
		// System.out.println(extractObligationHandlerIdentifier2(f));
		// }

	}

	public static void parseHttpResponseBodyToString() {

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet("https://kodejava.org");

		try {
			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();

			// Read the contents of an entity and return it as a String.
			String content = EntityUtils.toString(entity);
			System.out.println(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String extractObligationHandlerIdentifier2(String fragment) {

		Pattern handlerPattern = Pattern.compile("(\\w+),(\\w+)");
		Matcher handlerMatcher = handlerPattern.matcher(fragment.replaceAll(",[0-9]+$", ""));
		return handlerMatcher.matches() ? handlerMatcher.group(2) : "DEFAULT";
	}

	private static String extractObligationHandlerIdentifier(String fragment) {
		Pattern noIdentifierPattern = Pattern.compile("(.+)(,\\d+)$");
		Matcher noIdentifierMatcher = noIdentifierPattern.matcher(fragment);

		String input = noIdentifierMatcher.matches() ? noIdentifierMatcher.group(1) : fragment;

		Pattern handlerPattern = Pattern.compile("(\\w+),(\\w+)");
		Matcher handlerMatcher = handlerPattern.matcher(input);
		return handlerMatcher.matches() ? handlerMatcher.group(2) : "DEFAULT";
	}

	public static void testRegex(List<String> fragments) {
		System.err.print(" testing \n");
		String defHandler = "DEFAULT";

		for (String f : fragments) {
			String[] split = f.split(",");
			switch (split.length) {
			case 1:
				System.out.println(defHandler);
				break;
			case 2:
				System.out.println(defHandler);
				break;

			case 3:
				Pattern pattern = Pattern.compile("(\\w+),(\\w+),(.+)");
				Matcher matcher = pattern.matcher(f);
				if (matcher.matches()) {
					System.out.println(matcher.group(2));
				} else {
					System.out.println(defHandler);
				}
				break;
			}
		}

		String[] split = "test".split(",");
		Arrays.stream(split).forEach(System.out::println);
	}

}
