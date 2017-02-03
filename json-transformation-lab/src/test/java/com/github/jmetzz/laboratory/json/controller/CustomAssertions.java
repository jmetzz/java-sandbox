package com.github.jmetzz.laboratory.json.controller;

import guru.nidi.ramltester.RamlDefinition;
import guru.nidi.ramltester.RamlLoaders;
import org.assertj.core.api.Assertions;
import org.springframework.test.web.servlet.ResultActions;

public class CustomAssertions extends Assertions {
    private static RamlDefinition api;

    static {
        api = RamlLoaders
                .fromClasspath("api")
                .load("sales.raml")
                .assumingBaseUri("http://jmetzz.github.com");
    }

    public static ResultActionsAssert assertThat(ResultActions resultActions) {
        return new ResultActionsAssert(resultActions, api);
    }
}
