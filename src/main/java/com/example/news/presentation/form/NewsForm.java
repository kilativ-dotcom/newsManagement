package com.example.news.presentation.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class NewsForm {

    @NotNull
    @Size(min=1, max=100)
    private String title;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @NotNull
    @Size(min=1, max=500)
    private String brief;

    @NotNull
    @Size(min=1, max=2048)
    private String content;

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "NewsForm{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", brief='" + brief + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
