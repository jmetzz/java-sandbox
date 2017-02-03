package com.github.jmetzz.laboratory.json_processing.jsonp;


import com.github.jmetzz.laboratory.json_processing.pojos.Book;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class JsonPTest {


    @Test
    public void m(){

        Book book = new Book();
        book.setAuthor("John Doe");
        book.setTitle( "My title");
        book.setIsbn("923879384793");
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
                .add("firstName", book.getTitle())
                .add("lastName", book.getAuthor())
                .add("ISBN", book.getIsbn());


        String jsonString;
        try(Writer writer = new StringWriter()) {
            Json.createWriter(writer).write(objectBuilder.build());
            jsonString = writer.toString();
            System.out.println(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
