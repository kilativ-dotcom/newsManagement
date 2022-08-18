package com.example.news.service;

import com.example.news.database.NewsRepository;
import com.example.news.model.News;
import com.example.news.presentation.form.NewsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class NewsService {

    private NewsRepository newsRepository;

    @Autowired
    public void setNewsRepository(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public News save(News news){
        return newsRepository.save(news);
    }

    public Iterable<News> getAll(){
        return newsRepository.findAllByOrderByDate();
    }

    public Optional<News> getById(long id){
        return newsRepository.findById(id);
    }

    public News newsFromForm(NewsForm newsForm){
        News news = new News();
        news.setTitle(newsForm.getTitle());
        news.setDate(newsForm.getDate());
        news.setBrief(newsForm.getBrief());
        news.setContent(newsForm.getContent());
        return news;
    }

    public NewsForm formFromNews(News news){
        NewsForm newsForm = new NewsForm();
        newsForm.setTitle(news.getTitle());
        newsForm.setDate(news.getDate());
        newsForm.setBrief(news.getBrief());
        newsForm.setContent(news.getContent());
        return newsForm;
    }

    public void delete(long id){
        if (newsRepository.existsById(id)) {
            newsRepository.deleteById(id);
        }
    }

    public void delete(long[] ids){
        Arrays.stream(ids).forEach(this::delete);
    }

}
