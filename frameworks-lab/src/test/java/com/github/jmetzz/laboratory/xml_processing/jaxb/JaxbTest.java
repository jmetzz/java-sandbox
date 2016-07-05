package com.github.jmetzz.laboratory.xml_processing.jaxb;

import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class JaxbTest {

    private static final String PACKAGE_RESOURCES = "src/test/resources/xml-resources/";
    private static final String BOOKSTORE_XML = PACKAGE_RESOURCES + "books-collection-jaxb.xml";
    private static BookCollection bookcollection;

    @BeforeClass
    public static void setupData(){
        bookcollection = createBookCollection();
    }

    public static final String creditCardXML =
            "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                    "<creditCard number=\"12345678\">\n" +
                    "    <expiry_date>10/14</expiry_date>\n" +
                    "    <control_number>566</control_number>\n" +
                    "    <type>Visa</type>\n" +
                    "</creditCard>";

    @Test
    public void shouldMarshallACreditCard() throws JAXBException {

        CreditCard creditCard = new CreditCard("12345678", "10/14", 566, "Visa");

        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(CreditCard.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(creditCard, writer);

        System.out.println(writer);

        assertEquals(creditCardXML, writer.toString().trim());

    }

    @Test
    public void shouldUnmarshallACreditCard() throws JAXBException {
        StringReader reader = new StringReader(creditCardXML);
        JAXBContext context = JAXBContext.newInstance(CreditCard.class);
        Unmarshaller u = context.createUnmarshaller();
        CreditCard creditCard = (CreditCard) u.unmarshal(reader);

        assertEquals("12345678", creditCard.getNumber());
        assertEquals("10/14", creditCard.getExpiryDate());
        assertEquals((Object) 566, creditCard.getControlNumber());
        assertEquals("Visa", creditCard.getType());
    }

    @Test
    public void shouldMarshallABookCollection() throws JAXBException {
        // create JAXB context and instantiate marshaller
        JAXBContext context = JAXBContext.newInstance(BookCollection.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // Write to System.out
        m.marshal(bookcollection, System.out);

        // Write to File
        m.marshal(bookcollection, new File(BOOKSTORE_XML));

    }

    @Test
    public void shouldUnmarshallABookCollection() throws JAXBException, FileNotFoundException {

        JAXBContext context = JAXBContext.newInstance(BookCollection.class);
        Unmarshaller um = context.createUnmarshaller();
        BookCollection collection2 = (BookCollection) um.unmarshal(new FileReader(BOOKSTORE_XML));
        List<Book> list = collection2.getBooksList();

        System.out.println();
        System.out.println("Output from our XML File: ");
        for (Book book : list) {
            System.out.println(book);
        }
    }

    @Test
    public void shouldMarchallAndValidateABookCollection() throws JAXBException, SAXException {

        fail("Still need to define the xsd for this test!");

        JAXBContext context = JAXBContext.newInstance(BookCollection.class);

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(Paths.get(PACKAGE_RESOURCES + "book-collection.xsd").toFile());

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setSchema(schema);

        // Write to System.out
        marshaller.marshal(bookcollection, System.out);

        // Write to File
        marshaller.marshal(bookcollection, new File(BOOKSTORE_XML));
    }




    private static BookCollection createBookCollection(){
        List<Book> booklist = new ArrayList<>();

        Book book1 = new Book();
        book1.setIsbn("978-0060554736");
        book1.setTitle("The Game");
        book1.setEdition(1);
        book1.setAuthor("Neil Strauss");
        book1.setPublisher("HarperCollins");
        book1.setIllustrated(false);

        Book book2 = new Book();
        book2.setIsbn("978-0060530510");
        book2.setTitle("The last kingdom");
        book2.setEdition(1);
        book2.setAuthor("Bernard Cornwell");
        book2.setPublisher("HarperCollins");
        book2.setIllustrated(false);

        booklist.add(book1);
        booklist.add(book2);

        BookCollection collection = new BookCollection();
        collection.setName("Fiction");
        collection.setBookList(booklist);

        return collection;
    }

}