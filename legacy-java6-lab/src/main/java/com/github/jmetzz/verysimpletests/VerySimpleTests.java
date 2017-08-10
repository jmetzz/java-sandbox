package com.github.jmetzz.verysimpletests;


import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerySimpleTests {


    public static enum IDTYPE   {
        USER("x-ravago-user"),  GROUP("x-ravago-group"),  SEARCH("x-ravago-search");

        private String value;

        private IDTYPE(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }



    public enum CriteriaTag {
        DRAFT(10),
        NEED_ATTENTION(11),
        TO_BE_ACCEPTED(20),
        NEED_FOR_CREDIT(30),
        VALIDATED(40),
        NEED_FOR_ALLOCATION(50),
        FAILED_CREDIT_CHECK(60),
        ACCEPTED(70),
        IN_DELIVERY(80);


        private long id;

        CriteriaTag(long id) {
            this.id = id;
        }

        public long getId() {
            return id;
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {


        CriteriaTag[] tagsToRemove = {CriteriaTag.ACCEPTED, CriteriaTag.DRAFT};
        System.out.format("Removing TAGs %s", Arrays.asList(tagsToRemove));


        IDTYPE  e = IDTYPE.USER;

        System.out.println(e.toString());


/*
        List<String> stringList = new ArrayList<String>();
        stringList.add("AAAXXXDDD12");
        stringList.add("AAAXXXDDD12DDD");
        stringList.add("AAAXXXDDD12_1");
        stringList.add("AAAXXXDDD1");
        stringList.add("AAAXXXDDD_1");
        Pattern pattern = Pattern.compile("(\\d+)$");
        for (String text : stringList) {
            Matcher matcher = pattern.matcher(text);
            System.out.println(text );
            if (matcher.find()) {
                int start = matcher.start();
                System.out.print(String.format("%" + (start + 1) + "s", "^"));
                System.out.println("\t| Chopped string is: " + text.substring(0, matcher.start()));
            } else {
                System.out.print(String.format("%" + (text.length()+  1) + "s", "^"));
                System.out.println("\t| Pattern not found");
            }
        }


        System.out.println("--------------------------------");

        Pattern PLACEHOLDER_END_PATTERN = Pattern.compile("(\\*XX\\*)$");
        stringList.add("123456*XX*");
        stringList.add("123456*XX*bbbb");
        for (String text : stringList) {
            Matcher placeHolderMatcher = PLACEHOLDER_END_PATTERN.matcher(text);

            if (placeHolderMatcher.find()) {
                System.out.println("Place holder pattern found: " + placeHolderMatcher.group());
                System.out.println("\tChopped string is: " + text.substring(0, placeHolderMatcher.start()));

            } else {
                System.out.println("Place holder pattern NOT found");
            }
        }

        System.out.println("--------------------------------");

        String text = "This is the text which is to be searched for occurrences of the word 'is'.";
        String patternString = "is";
        pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);
        int count = 0;
        while (matcher.find()) {
            count++;
            System.out.println("found: " + count + " : " + matcher.start() + " - " + matcher.end());
        }*/


/*
        System.out.println(Boolean.parseBoolean("true"));
        System.out.println(Boolean.parseBoolean("false"));
        System.out.println(Boolean.parseBoolean(null));
        System.out.println(Boolean.parseBoolean("other"));

//        long l = Long.parseLong(null);
//        System.out.println(l);

        Map<String, String[]> params = new TreeMap<String, String[]>();

        params.put("p1", new String[] {"a", "b"});
        params.put("p2", null);
        params.put("p3", new String[] {"a", "b"});

        StringBuffer sb = new StringBuffer();
        sb.append(params.get("p1")[0])
                .append("\n")
                .append(Optional.fromNullable(params.get("p2")).orNull())
                .append("\n")
                .append(params.get("p3")[0]);

        System.out.println(sb.toString());
        System.out.println("-------------------");
        System.out.println("notEmpty(\"one\", \"two\", \"three\") : " +  notEmpty("one", "two", "three"));
        System.out.println("notEmpty(\"one\", \"two\", \"\") : " + notEmpty("one", "two", ""));
        System.out.println("notEmpty(\"one\", \"two\", null) : " + notEmpty("one", "two", null));


        String tempStr = " true ";

        if (tempStr != null && tempStr.trim().equalsIgnoreCase("true")) {
            System.out.println("The tempStr content is TRUE");
        }
*/



/*
        if(Boolean.parseBoolean(StringUtils.trim(tempStr))){
            System.out.println("The tempStr content is TRUE");
        } else {
            System.out.println("The tempStr content is NOT TRUE");
        }
*/


    }


    private static boolean notEmpty(String... params) {
        for (String p : params) {
            if (StringUtils.isEmpty(p))
                return false;
        }
        return true;
    }
}
