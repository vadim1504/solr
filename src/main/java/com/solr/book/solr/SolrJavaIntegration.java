package com.solr.book.solr;

import com.solr.book.model.BookBean;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Configuration
public class SolrJavaIntegration {

    @Autowired
    private HttpSolrClient solrClient;

    public void addBookBean(BookBean pBean) throws IOException, SolrServerException {
        solrClient.addBean(pBean);
        solrClient.commit();
    }

    public void addSolrDocument(int id, String name, String author, int date, String publisher, String body) throws SolrServerException, IOException {
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", id);
        document.addField("name", name);
        document.addField("author", author);
        document.addField("date", date);
        document.addField("publisher", publisher);
        document.addField("body", body);
        solrClient.add(document);
        solrClient.commit();
    }

    public void deleteSolrDocumentById(String documentId) throws SolrServerException, IOException {
        solrClient.deleteById(documentId);
        solrClient.commit();
    }

    public void deleteSolrDocumentByQuery(String query) throws SolrServerException, IOException {
        solrClient.deleteByQuery(query);
        solrClient.commit();
    }

    public BookBean getBook(String name) throws IOException, SolrServerException {
        SolrQuery query = new SolrQuery();
        query.set("q","name:"+name);
        QueryResponse response = solrClient.query(query);
        return response.getBeans(BookBean.class).get(0);
    }
    public List<BookBean> search(String text) throws IOException, SolrServerException {
        SolrQuery query = new SolrQuery();
        query.setQuery(text);
      /*  query.setHighlight(true);
        query.addHighlightField("fullText");
        query.setHighlightSimplePre("<strong>");
        query.setHighlightSimplePost("</strong>");*/
        QueryResponse response = solrClient.query(query);

      /*  Map<String, Map<String, List<String>>> hitHighlightedMap = response.getHighlighting();

        Map<String, List<String>> highlightedFieldMap = hitHighlightedMap.get("02e1f9d1-8e17-456d-83d2-15552c196ff3");
        List<String> highlightedList = highlightedFieldMap.get("fullText");
        String highLightedText = highlightedList.get(0);
*/

        return response.getBeans(BookBean.class);
    }
}
