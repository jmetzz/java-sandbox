package com.github.jmetzz.laboratory.json_processing.pojos;


import java.util.Map;

public class Book {

    private int id;
    private String author;
    private String title;
    private String publisher;
    private String isbn;
    private int edition;
    private boolean illustrated;

    private Map obj;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public boolean isIllustrated() {
        return illustrated;
    }

    public void setIllustrated(boolean illustrated) {
        this.illustrated = illustrated;
    }

    public Map getObj() {
        return obj;
    }

    public void setObj(Map obj) {
        this.obj = obj;
    }
}
