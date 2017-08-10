package com.github.jmetzz.laboratory.general_java.strings;


import java.text.Collator;
import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.*;

public class CompareAndSortStringsDemo {

//    simpleUnicodeOrdering
//    localizedOrdering
//    The fundamental difference is that localized comparison depends on Locale, while String is largely ignorant of Locale.

    /*
    The Java Programming Language by Arnold, Gosling, and Holmes:
        "You should be aware that internationalization and localization issues of full Unicode strings
        are not addressed with [String] methods. For example, when you're comparing two strings
        to determine which is 'greater', characters in strings are compared numerically by their Unicode values,
        not by their localized notion of order."
    */


    private static String[] emails = {"_abc_@testmail.com", "tts2ux1@yahoo.com", "yaramillie56@testmail.com",
            "_abc2_@testmail.com", "urg@hotmail.com", "yaux2aux@yahoo.com", "vision@hotmail.com",
            "_abc45_@testmail.com", "west@gmail.com", "tukkka@yahoo.com", "trat@testmail.com"};

    private static String[] wordsWithUnderscore = {"EMAIL", "SCORING", "AMOUNT", "CREDITDEBIT", "CURRENCY",
            "PAYMENT_REFERENCE", "SCO_CATEGORY", "STATUS", "ACCEPTANCE"};

    //This data is based on an example in Java Class Libraries,
    //by Chan, Lee, and Kramer
    private static String[] accentuatedWords = {"Äbc", "äbc", "Àbc", "àbc", "Abc", "abc", "ABC"};

    private static Locale LOCALE = Locale.FRANCE;


    public static void main(String[] args) throws ParseException {

        System.out.println("--------- Testing Map Ordering using Unicode (default java) string comparison  ---------");
        testMapOrdering();

        System.out.println("--------- Testing String comparison using Unicode and Localized methods ---------");
        testStringComparison();

        System.out.println("--------- Testing list ordering using Unicode and Localized methods ---------");
        testListOrdering();

        System.out.println("--------- Testing Java Sorted Collections ordering using Unicode and Localized methods ---------");
        testSortedCollection();

        System.out.println("--------- Testing Customization on collation rules ---------");
        testCustomizedCollationRules();

    }

    private static void testCustomizedCollationRules() throws ParseException {
    /* Customized Collation Rules
    * {@link #http://docs.oracle.com/javase/7/docs/api/java/text/RuleBasedCollator.html}
    * It is possible to customize the rules used to compare strings using the RuleBasedCollator. Here is a simple example:
    * */
        String rules = "< b < a";
        RuleBasedCollator ruleBasedCollator = null;
        ruleBasedCollator = new RuleBasedCollator(rules);
        int result = ruleBasedCollator.compare("a", "b");
        System.out.println(result);

        /*You can group characters by separating them with a comma in the rule string*/
        rules = "< c,C < b,B";
        ruleBasedCollator = new RuleBasedCollator(rules);
        result = ruleBasedCollator.compare("boss", "Carol");
        System.out.println(result);

        /*Combinations of Characters Interpreted as One Character
        * Some combinations of characters are to be interpreted as one character when ordering strings
        * */
        rules = "< ch < b < a < c";
        ruleBasedCollator = new RuleBasedCollator(rules);
        result = ruleBasedCollator.compare("boss", "carol");
        System.out.println(result);
        result = ruleBasedCollator.compare("boss", "charlie");
        System.out.println(result);
    }

    private static void testMapOrdering() {
        Map<String, String> countriesAndCapitals = new LinkedHashMap<>();
        countriesAndCapitals.put("finland", "Helsinki");
        countriesAndCapitals.put("United States", "Washington");
        countriesAndCapitals.put("Mongolia", "Ulan Bator");
        countriesAndCapitals.put("Canada", "Ottawa");

        System.out.println("Original:");
        System.out.println(countriesAndCapitals);
        System.out.println("Sorted:");
        System.out.println(UnicodeStringSort.sort(countriesAndCapitals));

    }


    private static void testStringComparison() {
        System.out.println("Comparing (abc, ÀBC)[UNICODE] => " + "abc".compareTo("ÀBC"));
        System.out.println("Comparing (ABC, ÀBC)[UNICODE] => " + "ABC".compareTo("ÀBC"));
        System.out.println("Comparing (ÀBC, ÀBC)[UNICODE] => " + "ÀBC".compareTo("ÀBC"));
        System.out.println("\n");
        System.out.println("Comparing (abc, ÀBC)[Primary] => " + LocalizedStringSort.compare("abc", "ÀBC", LocalizedStringSort.Strength.Primary, LOCALE));
        System.out.println("Comparing (abc, ÀBC)[Secondary] => " + LocalizedStringSort.compare("abc", "ÀBC", LocalizedStringSort.Strength.Secondary, LOCALE));
        System.out.println("Comparing (abc, ÀBC)[Tertiary] => " + LocalizedStringSort.compare("abc", "ÀBC", LocalizedStringSort.Strength.Tertiary, LOCALE));
        System.out.println("\n");
        System.out.println("Comparing (ABC, ÀBC)[Primary] => " + LocalizedStringSort.compare("ABC", "ÀBC", LocalizedStringSort.Strength.Primary, LOCALE));
        System.out.println("Comparing (ABC, ÀBC)[Secondary] => " + LocalizedStringSort.compare("ABC", "ÀBC", LocalizedStringSort.Strength.Secondary, LOCALE));
        System.out.println("Comparing (ABC, ÀBC)[Tertiary] => " + LocalizedStringSort.compare("ABC", "ÀBC", LocalizedStringSort.Strength.Tertiary, LOCALE));
        System.out.println("Comparing (ABC, ÀBC)[Identical] => " + LocalizedStringSort.compare("ABC", "ÀBC", LocalizedStringSort.Strength.Identical, LOCALE));
        System.out.println("\n");
        System.out.println("Comparing (ÀBC, ÀBC)[Primary] => " + LocalizedStringSort.compare("ÀBC", "ÀBC", LocalizedStringSort.Strength.Primary, LOCALE));
        System.out.println("Comparing (ÀBC, ÀBC)[Secondary] => " + LocalizedStringSort.compare("ÀBC", "ÀBC", LocalizedStringSort.Strength.Secondary, LOCALE));
        System.out.println("Comparing (ÀBC, ÀBC)[Tertiary] => " + LocalizedStringSort.compare("ÀBC", "ÀBC", LocalizedStringSort.Strength.Tertiary, LOCALE));
        System.out.println("Comparing (ÀBC, ÀBC)[Identical] => " + LocalizedStringSort.compare("ÀBC", "ÀBC", LocalizedStringSort.Strength.Identical, LOCALE));
    }


    private static void testListOrdering() {
        List<String> emailList = Arrays.asList(emails);
        System.out.println("\tOriginal:");
        System.out.println(emailList);
        sortAndLogList(emailList, LOCALE);

        System.out.println("\nWhat happens when '_' is present in your elements?" );
        System.out.println("\tCheck out how SCORING and SCO_CATEGORY are ordered\n");
        System.out.print("\tOriginal:");
        List<String> words = Arrays.asList(wordsWithUnderscore);
        System.out.println(words);
        sortAndLogList(words, Locale.US);
    }

    private static void sortAndLogList(List<String> list, Locale locale) {
        System.out.println("Unicode Sorted:");
        UnicodeStringSort.sort(list);
        System.out.println(list);
        System.out.println("");

        Collections.shuffle(list);
        System.out.println("Locale Sorted - Strength.Primary: ");
        LocalizedStringSort.sort(list, LocalizedStringSort.Strength.Primary, locale);
        System.out.println(list);

        Collections.shuffle(list);
        System.out.println("Locale Sorted - Strength.Secondary: ");
        LocalizedStringSort.sort(list, LocalizedStringSort.Strength.Secondary, locale);
        System.out.println(list);

        Collections.shuffle(list);
        System.out.println("Locale Sorted - Strength.Tertiary: ");
        LocalizedStringSort.sort(list, LocalizedStringSort.Strength.Tertiary, locale);
        System.out.println(list);

        Collections.shuffle(list);
        System.out.println("Locale Sorted - Strength.Identical: ");
        LocalizedStringSort.sort(list, LocalizedStringSort.Strength.Identical, locale);
        System.out.println(list);
    }


    private static void testSortedCollection() {
        SortedSet<String> sortedSet = new TreeSet<>(Arrays.asList(accentuatedWords));
        System.out.println("Unicode sorted strings: ");
        System.out.println(sortedSet);

        List<String> list = new ArrayList<>(sortedSet);
        Collections.sort(list, Collator.getInstance(Locale.US));
        System.out.println("Locale dependent sorted strings (US) with default Strength: ");
        System.out.println(list);


        LocalizedStringSort.sort(list, LocalizedStringSort.Strength.Primary, LOCALE);
        System.out.println("Locale dependent sorted strings (US) with Strength.Primary: ");
        System.out.println(list);

        LocalizedStringSort.sort(list, LocalizedStringSort.Strength.Secondary, LOCALE);
        System.out.println("Locale dependent sorted strings (US) with Strength.Secondary: ");
        System.out.println(list);
    }


}
