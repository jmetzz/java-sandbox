package com.github.jmetzz.functional.guava.splitAdmission;

import org.apache.commons.lang3.StringUtils;

public class Matcher {


    public static boolean matchesWildCard(String pattern, String input, String wildCard) {
        if (input == null || wildCard == null) {
            return false;
        }
        if (pattern != null) {
            return input.startsWith(StringUtils.substringBefore(pattern, wildCard));
        } else {
            return true;
        }
    }

    public static boolean matchesWildCard(String pattern, String input, String wildCard, boolean inputNullable) {
        if (inputNullable && StringUtils.isEmpty(input)) {
            return true;
        } else {
            return matchesWildCard(pattern, input, wildCard);
        }
    }
}
