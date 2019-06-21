package com.sm.entity;

import java.util.Date;

public class SchoolNews {
    private Integer id;
    private String news;
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SchoolNews{" +
                "id=" + id +
                ", news='" + news + '\'' +
                ", date=" + date +
                '}';
    }
}
