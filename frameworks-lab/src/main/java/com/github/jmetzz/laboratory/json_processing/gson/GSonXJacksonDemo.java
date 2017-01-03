package com.github.jmetzz.laboratory.json_processing.gson;

import com.github.jmetzz.laboratory.json_processing.jackson.JacksonWrapper;
import com.github.jmetzz.laboratory.json_processing.JsonParserWrapper;
import com.github.jmetzz.laboratory.json_processing.pojos.Book;

import static com.github.jmetzz.laboratory.Constants.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GSonXJacksonDemo {

    private static final String SMALL_SIZE_DATA_JSON_PATH = JSON_TEST_RESOURCES + "small_size_data.json";
    private static final String MEDIUM_SIZE_DATA_JSON_PATH = JSON_TEST_RESOURCES + "medium_size_data.json";
    private static final String BIG_SIZE_DATA_JSON_PATH = JSON_TEST_RESOURCES + "big_size_data.json";

    private static final Logger LOGGER = Logger.getLogger(GSonXJacksonDemo.class.getName());

    public static void main(String[] args) throws IOException {
        JsonParserWrapper gSonWrapper = new GSonWrapper();
        JsonParserWrapper jacksonWrapper = new JacksonWrapper();
        String jsonStr = "{ \"author\": \"Isaac Newton\", \"title\" : \"EMC^2\", \"obj\" : {\"objint\" : {}} }";

        Book book = new Book();
        book.setAuthor("Isaac Newton");
        book.setTitle("EMC^2");


        System.out.println("----------------------------------------");
        System.out.println("              Serialization");
        System.out.println("----------------------------------------");
        checkSerialization(gSonWrapper, jacksonWrapper, jsonStr);


        System.out.println("----------------------------------------");
        System.out.println("              Deserialization");
        System.out.println("----------------------------------------");

        System.out.println("bookJson: " + gSonWrapper.deserialize(book));
        System.out.println("bookJson: " + jacksonWrapper.deserialize(book));


        System.out.println("----------------------------------------");
        System.out.println("              Performance");
        System.out.println("----------------------------------------");

        System.out.println("Small sized data ");
        System.out.printf("\tGSon -> \t");
        checkPerformance(gSonWrapper, SMALL_SIZE_DATA_JSON_PATH);
        System.out.printf("\tJackson -> \t");
        checkPerformance(jacksonWrapper, SMALL_SIZE_DATA_JSON_PATH);

        System.out.println("Medium sized data ");
        System.out.printf("\tGSon -> \t");
        checkPerformance(gSonWrapper, MEDIUM_SIZE_DATA_JSON_PATH);
        System.out.printf("\tJackson -> \t");
        checkPerformance(jacksonWrapper, MEDIUM_SIZE_DATA_JSON_PATH);

        System.out.println("Big sized data ");
        System.out.printf("\tGSon -> \t");
        checkPerformance(gSonWrapper, BIG_SIZE_DATA_JSON_PATH);
        System.out.printf("\tJackson -> \t");
        checkPerformance(jacksonWrapper, BIG_SIZE_DATA_JSON_PATH);
    }

    private static void checkSerialization(JsonParserWrapper gSonWrapper, JsonParserWrapper jacksonWrapper, String jsonStr) {
        try {
            parseJson(jsonStr, gSonWrapper); //com.google.gson.internal.LinkedTreeMap
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Gson could not parse the input", e);
        }
        try {
            parseJson(jsonStr, jacksonWrapper); //
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Jackson could not parse the input", e);
        }
    }

    private static void parseJson(String jsonStr, JsonParserWrapper json) throws IOException {

        Object obj = json.serialize(jsonStr);

        System.out.println("obj type: " + obj.getClass().toString()); // com.google.gson.internal.LinkedTreeMap
        System.out.println("obj: " + obj);

        Map m = json.serialize(jsonStr, Map.class);
        System.out.println("m: " + m.size());  // 3

        for (Object key : m.keySet()) {
            System.out.println("key:" + key);
        }

        Book book = json.serialize(jsonStr, Book.class);
        System.out.println("book: " + book);
        System.out.println("book.author: " + book.getAuthor());
        System.out.println("book.obj class: " + book.getObj().getClass());
        System.out.println("book.obj: " + book.getObj());
    }

    private static void checkPerformance(JsonParserWrapper json, String dataJsonPath) throws IOException {
        /* Random data generated using http://experiments.mennovanslooten.nl/2010/mockjson/tryit.html
        with the following template:

        {
            "books|5-1000" : [
            {
                "id|+1" : 0,
                    "author" : "@MALE_FIRST_NAME @LAST_NAME",
                    "title|2-4" : "@LOREM ",
                    "publisher" : "@LAST_NAME",
                    "isbn|9-9" : "@NUMBER",
                    "edition|1" : "@NUMBER",

                    "illustrated|0-1" : true,
                    "obj" :  { }

            }
            ]
        }

        Alternative tool: http://www.json-generator.com/# for generating a JSON file
        this tools is much better and expressive than the one I've used here.

        For a very big data set, please consider this one: https://github.com/zemirco/sf-city-lots-json

*/
        long start = System.nanoTime();

        json.serialize(Paths.get(dataJsonPath));

        long end = System.nanoTime();
        System.out.println("Time taken (nano seconds): " + (end - start));


    }

}
