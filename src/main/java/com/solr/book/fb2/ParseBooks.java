package com.solr.book.fb2;

import com.solr.book.model.BookBean;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ParseBooks {

    public static List<BookBean> getBooks(String path) throws ParserConfigurationException, IOException, SAXException, ParseException {
        File[] files = new File(path).listFiles();
        ArrayList<BookBean> bookBeans = new ArrayList<>();
        if(files!=null) {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            for (File file : files) {
                Document doc = docBuilder.parse(file.getPath());
                String date = doc.getElementsByTagName("date").item(0).getTextContent();
                int year = Integer.valueOf(date.substring(date.length() - 4, date.length()));


                bookBeans.add(new BookBean(doc.getElementsByTagName("book-title").item(0).getTextContent(), doc.getElementsByTagName("first-name").item(0).getTextContent() + " " + doc.getElementsByTagName("last-name").item(0).getTextContent(), year, ParseBooks.getTextBook(doc)));
            }
        }
        return bookBeans;
    }

    private static ArrayList<String> getTextBook(Document doc){
        ArrayList<String> p = new ArrayList<>();
        int n = doc.getElementsByTagName("p").getLength();
        for (int i=0;i<n;i++){
            p.add(doc.getElementsByTagName("p").item(i).getTextContent());
        }
        return p;
    }
}
