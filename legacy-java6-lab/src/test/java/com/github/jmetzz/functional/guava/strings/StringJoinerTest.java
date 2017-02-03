package com.github.jmetzz.functional.guava.strings;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by jean on 20/01/2017.
 */
public class StringJoinerTest {

    @Test
    public void should_ConvertListOfStringToString() {
        List<String> names = Lists.newArrayList("John", "Jane", "Adam", "Tom");
        String result = Joiner.on(",").join(names);
        System.out.println(result);
        assertEquals(result, "John,Jane,Adam,Tom");
    }

    @Test
     public void should_ConvertListOfNonStringElementsToString(){

        List<LogItem> list = Lists.newArrayList();
        list.add(new LogItem("key1", "value1"));
        list.add(new LogItem("key2", "value2"));
        list.add(new LogItem("key3", "value3"));

        String result = Joiner.on(", ").join(list);
        System.out.println(result);
        assertEquals(result, "key1=\"value1\", key2=\"value2\", key3=\"value3\"");
    }



    @Test
    public void whenConvertMapToString_thenConverted() {
        Map<String, Integer> salary = Maps.newHashMap();
        salary.put("John", 1000);
        salary.put("Jane", 1500);
        String result = Joiner.on(" , ").withKeyValueSeparator(" = ").join(salary);

        assertThat(result, containsString("John = 1000"));
        assertThat(result, containsString("Jane = 1500"));
    }


    class LogItem {
        private String key;
        private String value;

        public LogItem(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "=\"" + value + "\"";
        }
    }
}
