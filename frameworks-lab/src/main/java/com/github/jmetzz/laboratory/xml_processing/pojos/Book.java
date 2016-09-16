package com.github.jmetzz.laboratory.xml_processing.pojos;


import com.google.common.base.MoreObjects;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name = "book")
// Optional - we can define the order in which the fields are written:
@XmlType(propOrder = {"id", "author", "title", "edition", "publisher", "isbn", "illustrated"} )
public class Book {

    private int id;
    private String author;
    private String title;
    private String publisher;
    private String isbn;
    private int edition;
    private boolean illustrated;

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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("author", author)
                .add("title", title)
                .add("publisher", publisher)
                .add("isbn", isbn)
                .add("edition", edition)
                .add("illustrated", illustrated)
                .toString();
    }
}
