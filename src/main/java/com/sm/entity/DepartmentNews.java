package com.sm.entity;

import java.util.Date;

public class DepartmentNews {
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
        return "DepartmentNews{" +
                "id=" + id +
                ", news='" + news + '\'' +
                ", date=" + date +
                '}';
    }
}
