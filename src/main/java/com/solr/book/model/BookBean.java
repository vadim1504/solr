package com.solr.book.model;

import org.apache.solr.client.solrj.beans.Field;

import java.util.List;

public class BookBean {

    private String name;
    private String author;
    private long year;
    private List<String> fullText;

    public BookBean(){

    }

    public BookBean(String name, String author, int year, List<String> fullText){
        this.name=name;
        this.author=author;
        this.year=year;
        this.fullText=fullText;
    }

    public String getName() {
        return name;
    }
    @Field("name")
    public void setName(String name) {
        this.name = name;
    }
    public String getAuthor() {
        return author;
    }
    @Field("author")
    public void setAuthor(String author) {
        this.author = author;
    }
    public long getYear() {
        return year;
    }
    @Field("year")
    public void setYear(long year) {
        this.year = year;
    }
    public List<String> getFullText() {
        return fullText;
    }
    @Field("fullText")
    public void setFullText(List<String> fullText) {
        this.fullText = fullText;
    }
}
