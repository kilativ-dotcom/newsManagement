package com.example.news.database;

import com.example.news.model.News;
import org.springframework.data.repository.CrudRepository;

public interface NewsRepository extends CrudRepository<News, Long> {
    Iterable<News> findAllByOrderByDate();
    boolean existsById(long id);
}
