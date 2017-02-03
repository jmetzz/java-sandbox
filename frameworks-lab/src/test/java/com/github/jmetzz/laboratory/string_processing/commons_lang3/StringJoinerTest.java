package com.github.jmetzz.laboratory.string_processing.commons_lang3;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jean on 20/01/2017.
 */
public class StringJoinerTest {

    @Test
    public void should_ConvertListOfStringToString() {
        List<String> names = Lists.newArrayList("John", "Jane", "Adam", "Tom");
        String result = StringUtils.join(names.iterator(), ",");
        System.out.println(result);
        assertEquals(result, "John,Jane,Adam,Tom");
    }

    @Test
     public void should_ConvertListOfNonStringElementsToString(){

        List<LogItem> list = Lists.newArrayList();
        list.add(new LogItem("key1", "value1"));
        list.add(new LogItem("key2", "value2"));
        list.add(new LogItem("key3", "value3"));

        String result = StringUtils.join(list.iterator(), ", ");
        System.out.println(result);
        assertEquals(result, "key1=\"value1\", key2=\"value2\", key3=\"value3\"");
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
