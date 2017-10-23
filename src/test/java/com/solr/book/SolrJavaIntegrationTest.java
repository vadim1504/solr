package com.solr.book;

import com.solr.book.fb2.ParseBooks;
import com.solr.book.solr.SolrJavaIntegration;
import org.junit.Before;


public class SolrJavaIntegrationTest  {

    private SolrJavaIntegration solrJavaIntegration;

    @Before
    public void setUp() throws Exception {

        solrJavaIntegration = new SolrJavaIntegration("http://localhost:8983/solr/bookstore");
        solrJavaIntegration.addBookBean(ParseBooks.getBooks("books_fb2").get(0));
    }


}
