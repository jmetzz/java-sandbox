package com.github.jmetzz.laboratory.json_processing.jackson;

import java.io.IOException;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;

/**
 * Created by jean on 27/02/2017.
 */
public class ConverterExample {


    public static void main(String[] args)  {

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = converter.getObjectMapper().configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, false);
//        ObjectMapper mapper = converter.getObjectMapper().disable(DeserializationFeature.ACCEPT_FLOAT_AS_INT);

        testMapper(mapper, "{\"value\" : 2}");
        System.out.println("---------------");
        testMapper(mapper, "{\"value\" : 2.1}");

    }

    private static void testMapper(ObjectMapper mapper, String body) {
        try {
            Quantity quantity = mapper.readValue(body, Quantity.class);
            System.out.println(quantity);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static class Quantity {
        private int value;

        public Quantity() {}

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("value", value)
                    .toString();
        }
    }
}
