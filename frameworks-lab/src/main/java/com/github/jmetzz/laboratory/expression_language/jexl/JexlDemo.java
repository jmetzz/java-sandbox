package com.github.jmetzz.laboratory.expression_language.jexl;


import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.MapContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class JexlDemo {

    private static Logger LOGGER = LoggerFactory.getLogger(JexlDemo.class);



    private void parseArgumentIntoSecurityContext(String[] parameterNames, Object[] args,
                                                  Map<SecurityContextEnum, String> securityContext,
                                                  SecurityContextEnum contextFieldName, String argument) {
        if (argument.isEmpty()) {
            return;
        }

        JexlContext jexlContext = new MapContext();
        for (int index = 0; index < args.length; index++) {
            jexlContext.set(parameterNames[index], args[index]);
        }
        Object value = new JexlBuilder().create().createExpression(argument).evaluate(jexlContext);
        storeValueInSecurityContext(securityContext, contextFieldName, value);
    }

    private void storeValueInSecurityContext(Map<SecurityContextEnum, String> securityContext,
                                             SecurityContextEnum contextFieldName,
                                             Object value) {

        if (value instanceof HasReference) {
            HasReference reference = (HasReference) value;
            securityContext.put(contextFieldName, reference.getReference());
        } else if (value instanceof String) {
            securityContext.put(contextFieldName, String.valueOf(value));
        } else if (value instanceof JsonNode) {
            securityContext.put(contextFieldName, ((JsonNode) value).asText());
        } else {
            LOGGER.warn("@AuthorizedResource argument field '{}' is not a Reference, JsonNode or a String, but is a '{}' !",
                    contextFieldName,
                    value.getClass().getName()
            );
            securityContext.put(contextFieldName, String.valueOf(value));
        }
    }
}
