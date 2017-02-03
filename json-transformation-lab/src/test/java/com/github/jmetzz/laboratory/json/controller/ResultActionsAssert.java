package com.github.jmetzz.laboratory.json.controller;

import com.github.jmetzz.laboratory.json_processing.jackson.JsonUtils;
import guru.nidi.ramltester.RamlDefinition;
import org.assertj.core.api.AbstractAssert;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by jean on 3/01/2017.
 */
public class ResultActionsAssert extends AbstractAssert<ResultActionsAssert, ResultActions> {
    private static RamlDefinition api;

    public ResultActionsAssert(ResultActions actual) {
        super(actual, ResultActionsAssert.class);
    }

    public ResultActionsAssert(ResultActions actual, RamlDefinition api) {
        super(actual, ResultActionsAssert.class);
        ResultActionsAssert.api = api;
    }


    public ResultActionsAssert matches(String jsonFilePath) throws Exception {
        String expectedJson = JsonUtils.fromFile(jsonFilePath).asText();

        String actualJson = actual
                .andDo(print())
                .andReturn().getResponse()
                .getContentAsString();
        JSONAssert.assertEquals(expectedJson, actualJson, true);
        return this;
    }

    public ResultActionsAssert andExpect(ResultMatcher resultMatcher) throws Exception {
        actual.andExpect(api.matches()).andExpect(resultMatcher);
        return this;
    }

    public ResultActionsAssert andMatchesRamlApi() throws Exception {
        actual.andExpect(api.matches());
        return this;
    }

}
