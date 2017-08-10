package com.github.jmetzz.apachecommons.cli.javacollections;

import java.util.*;

public class ParseOptions {

    private Map<String, String> options;

    private Set<String> mandatoryOpt;

    private Set<String> allOpt;

    public ParseOptions(Set<String> mOpt, Set<String> mFlag, Set<String> oOpt, Set<String> oFlag) {
        mandatoryOpt = new TreeSet<String>();
        mandatoryOpt.addAll(mOpt);
        mandatoryOpt.addAll(mFlag);

        allOpt = new TreeSet<String>(mandatoryOpt);
        allOpt.addAll(oOpt);
        allOpt.addAll(oFlag);
    }

    public void parse(String[] args) throws Exception {
        if (args.length == 0)
            throw new IllegalArgumentException("Missing arguments");

        options = new LinkedHashMap<String, String>();
        boolean value = false;

        // parses the command line options
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--")) {
                // if option
                if (allOpt.contains(args[i])) {
                    options.put(args[i], null);
                    value = true;
                } else {
                    throw new IllegalArgumentException("Invalid option: " + args[i]);
                }
            } else if (args[i].startsWith("-")) {
                // if flag
                if (allOpt.contains(args[i])) {
                    options.put(args[i], "yes");
                } else {
                    throw new IllegalArgumentException("Invalid flag: " + args[i]);
                }
            } else if (value) { // if argument value
                options.put(args[i - 1], args[i]);
                value = false;
            } else {
                throw new IllegalArgumentException("Invalid option: " + args[i]);
            }
        }
        validate(options);
    }

    private boolean validate(Map<String, String> attrMap) throws Exception {
        boolean valid = true;
        Set<String> attrs = attrMap.keySet();

        if (!attrs.containsAll(mandatoryOpt)) {
            Set<String> missing = new HashSet<String>(mandatoryOpt);
            missing.removeAll(attrs);
            throw new IllegalArgumentException("Missing required argument: " + missing);
        }
        if (!allOpt.containsAll(attrs)) {
            Set<String> illegal = new HashSet<String>(attrs);
            illegal.removeAll(allOpt);
            throw new IllegalArgumentException("Illegal attributes: " + illegal);
        }
        return valid;
    }

    public Map<String, String> getOptions() {
        return options;
    }
}
