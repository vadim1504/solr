package com.solr.book.controllers;

import com.solr.book.model.BookBean;
import com.solr.book.solr.SolrJavaIntegration;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private SolrJavaIntegration solr;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/search")
    public String searchText(ModelMap modal, @RequestParam(name = "text") String text) {
        List<BookBean> bookBeans = null;
        try {
            bookBeans = solr.search(text);
        } catch (IOException | SolrServerException e) {
            e.printStackTrace();
        }
        modal.addAttribute("result", bookBeans);
        return "index";
    }

    @RequestMapping("/view")
    public String viewBook(ModelMap modal, @RequestParam(name = "name") String name) {
        BookBean bookBeans = null;
        try {
            bookBeans = solr.getBook(name);
        } catch (IOException | SolrServerException e) {
            e.printStackTrace();
        }
        List<List<String>> pages = new ArrayList<>();
        List<String> page = new ArrayList<>();
        for(int i=0;i<bookBeans.getFullText().size();i++){
            page.add(bookBeans.getFullText().get(i));
            if(page.size()==10){
                pages.add(new ArrayList<>(page));
                page.clear();
            }
        }
        pages.add(page);
        modal.addAttribute("pages", pages);
        return "viewer";
    }
}