package com.github.jmetzz.laboratory.json_processing.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static com.github.jmetzz.laboratory.json_processing.jackson.JsonTransformer.transform;
import static com.github.jmetzz.laboratory.json_processing.jackson.JsonUtils.toJson;


/**
 * Created by jean on 3/01/2017.
 */
@RunWith(Parameterized.class)
public class JsonTransformerTest {

    private String input;
    private String expected;
    private String spec;

    public JsonTransformerTest(String input, String expected, String spec) {
        this.input = input;
        this.expected = expected;
        this.spec = spec;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new String[][]{
                {
                        "null", // check for null node
                        "null",
                        "/json-resources/spec-transformation-change-field-names.json"
                },

                {
                        "{ \"ID\" : 234, \"firstName\" : \"John\", \"lastName\" : \"Doe\"}",
                        "{ \"transformedID\" : 234, \"firstName\" : \"John\", \"familyName\" : \"Doe\"}",
                        "/json-resources/spec-transformation-change-field-names.json"
                },

                {
                        "{\"rating\": { \"primary\": {\"value\": 3}, \"quality\": {\"value\": 3}}}",
                        "{\"Range\" : 5, \"Rating\" : 3, \"SecondaryRatings\" : {\"quality\" : {\"Id\" : \"quality\", \"Range\" : 5, \"Value\" : 3 } } }",
                        "/json-resources/spec-transformation-example1.json"
                },

                {
                        "{\"Photos\": [{\"Id\": \"327703\", \"Caption\": \"TEST>> photo 1\", \"Url\": \"http://bob.com/0001/327703/photo.jpg\"}, {\"Id\": \"327704\", \"Caption\": \"TEST>> photo 2\", \"Url\": \"http://bob.com/0001/327704/photo.jpg\"} ] }",
                        "{\"photo-0-caption\" : \"TEST>> photo 1\", \"photo-0-id\" : \"327703\", \"photo-0-url\" : \"http://bob.com/0001/327703/photo.jpg\", \"photo-1-caption\" : \"TEST>> photo 2\", \"photo-1-id\" : \"327704\", \"photo-1-url\" : \"http://bob.com/0001/327704/photo.jpg\"}",
                        "/json-resources/spec-transformation-list-to-map-example.json"
                },

                {
                        "{\"ratings\": {\"primary\": 5, \"quality\": 4, \"design\": 5 } }",
                        "{\"Ratings\" : [ {\"Name\" : \"primary\", \"Value\" : 5 }, {\"Name\" : \"quality\", \"Value\" : 4 }, {\"Name\" : \"design\", \"Value\" : 5 } ] }",
                        "/json-resources/spec-transformation-map-to-list-example.json"
                },

                {
                        "{\"entities\": [{\"type\": \"alpha\", \"data\": \"foo\"}, {\"type\": \"beta\", \"data\": \"bar\"}, {\"type\": \"alpha\", \"data\": \"zoo\"} ] }",
                        "{\"alpha\" : [ {\"data\" : \"foo\", \"type\" : \"alpha\"}, {\"data\" : \"zoo\", \"type\" : \"alpha\"} ], \"beta\" : [ {\"data\" : \"bar\", \"type\" : \"beta\"} ] }",
                        "/json-resources/spec-transformation-bucket-data-from-array.json"
                },

                {
                        "{\"clientsActive\": true, \"clients\": {\"Acme\": {\"clientId\": \"Acme\", \"index\": 1 }, \"Axe\": {\"clientId\": \"AXE\", \"index\": 0 } }, \"data\": {\"bookId\": null, \"bookName\": \"Enchiridion\"} }",
                        "{\"clientIds\" : [ \"AXE\", \"Acme\" ] }",
                        "/json-resources/spec-transformation-complex-nested-transpose.json"
                },

                {
                        "{\"books\": [{\"title\": \"foo\", \"availability\": [\"online\"] }, {\"title\": \"bar\", \"availability\": [\"online\", \"paperback\"] }, {\"title\": \"baz\", \"availability\": [\"paperback\"] } ] }",
                        "{\"PaperBooks\" : [ {\"availability\" : [ \"online\", \"paperback\" ], \"title\" : \"bar\"}, {\"availability\" : [ \"paperback\" ], \"title\" : \"baz\"} ] }",
                        "/json-resources/spec-transformation-filter-data-from-array.json"
                },

                {
                        "{\"data\" : {\"1234\": {\"clientId\": \"12\", \"hidden\": true }, \"1235\": {\"clientId\": \"35\", \"hidden\": false } } }",
                        "{\"clients\" : {\"12\" : \"disabled\", \"35\" : \"enabled\"} }",
                        "/json-resources/spec-transformation-on-matching-apply-default.json"
                },

                {
                        "{\"restaurantId\": \"ZZ4ORJDY3E\", \"chainId\": \"RLR932KI\", \"orderItems\": [{\"itemName\": \"Small Barqs\", \"quantity\": 2 }, {\"itemName\": \"Mozzz\", \"quantity\": 1 } ] }",
                        "{\"basket_item\" : [ {\"Small Barqs\" : 2 }, {\"Mozzz\" : 1 } ], \"retailer_id\" : \"RLR932KI\", \"store_id\" : \"ZZ4ORJDY3E\"}",
                        "/json-resources/spec-transformation-transpose-data-into-array.json"
                }

/*
                "operation": "modify-overwrite-beta" not working in the current release
                {
                        "{\"statistics\": [{\"id\": \"A\", \"min\": \"2.0\", \"max\": \"10.0\", \"avg\": \"7.9\"}, {\"min\": \"6\", \"max\": \"6\", \"avg\": \"6\"}, {\"id\": \"C\"} ] }",
                        "{\"statistics\" : [ {\"avg\" : 7.9, \"id\" : \"A\", \"max\" : 10, \"min\" : 2 }, {\"avg\" : 6.0, \"id\" : \"UNKNOWN\", \"max\" : 6, \"min\" : 6 }, {\"avg\" : null, \"id\" : \"C\", \"max\" : null, \"min\" : 0 } ] }",
                        "/json-resources/spec-transformation-type-conversion.json"
                },

                {
                        "{\"people\" : [{\"firstName\" : \"Bob\", \"lastName\" : \"Smith\", \"address\" : {\"state\" : null } }, {\"firstName\" : \"Sterling\", \"lastName\" : \"Archer\"} ] }",
                        "{\"people\" : [ {\"address\" : {\"state\" : \"Texas\"}, \"firstName\" : \"Bob\", \"fullName\" : \"Bob Smith\", \"lastName\" : \"Smith\"}, {\"firstName\" : \"Sterling\", \"fullName\" : \"Sterling Archer\", \"lastName\" : \"Archer\"} ] }",
                        "/json-resources/spec-transformation-string-concatenation.json"
                },

                {
                        "{\"scores\" : [ 4, 2, 8, 7, 5 ] }",
                        "{\"firstScore\" : 4, \"lastScore\" : 5, \"scoreAtMidPoint\" : 8, \"scores\" : [ 4, 2, 8, 7, 5 ] }",
                        "/json-resources/spec-transformation-list-functions.json"
                }*/


        });
    }

    @Test
    public void should_transform() throws IOException, JSONException {
        JsonNode jsonNode = toJson(input);
        JsonNode actual = transform(jsonNode, spec);
        JSONAssert.assertEquals(expected, actual.toString(), false);
    }

}