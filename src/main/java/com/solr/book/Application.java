package com.solr.book;

import com.solr.book.fb2.ParseBooks;
import com.solr.book.model.BookBean;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;

@SpringBootApplication(scanBasePackages = "com.solr.book")
public class Application {

    @Value("${solr.host}")
    private String solrHost;

    @Bean()
    public HttpSolrClient getHttpSolrClient(){
        HttpSolrClient solrClient = new HttpSolrClient.Builder("http://localhost:8983/solr/bookstore"/*solrHost*/).build();
        solrClient.setParser(new XMLResponseParser());
        return solrClient;
    }

    public static void main(String[] args) {
        try {
            initBooks();
        }catch (Exception e){
            e.printStackTrace();
        }
        SpringApplication.run(Application.class, args);
    }

    private static void initBooks() throws ParserConfigurationException, SAXException, ParseException, IOException, SolrServerException {
        HttpSolrClient solrClient = new HttpSolrClient.Builder("http://localhost:8983/solr/bookstore").build();
        for (BookBean b: ParseBooks.getBooks("books_fb2")){
            solrClient.addBean(b);
        }
        solrClient.commit();
    }

}
