package com.github.jmetzz.laboratory.general_java.strings;

import java.text.Collator;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Uses a Collator to perform localized sorting and comparison of Strings
 */
public class LocalizedStringSort {

    /**
     * Collator strength value. Used to set which differences are
     * considered significant during comparison.
     */
    public enum Strength {
        Primary(Collator.PRIMARY), //base char
        Secondary(Collator.SECONDARY), //base char + accent
        Tertiary(Collator.TERTIARY), // base char + accent + case
        Identical(Collator.IDENTICAL); //base char + accent + case + bits

        int getStrength() {
            return fStrength;
        }

        private int fStrength;

        private Strength(int aStrength) {
            fStrength = aStrength;
        }
    }

    public static void sort(List<String> list, Strength strength, Locale locale) {
        Collator collator = Collator.getInstance(locale);
        collator.setStrength(strength.getStrength());
        Collections.sort(list, collator);
    }

    public static int compare(String aThis, String aThat, Strength aStrength, Locale locale) {
        Collator collator = Collator.getInstance(locale);
        collator.setStrength(aStrength.getStrength());
        return collator.compare(aThis, aThat);
    }

}
