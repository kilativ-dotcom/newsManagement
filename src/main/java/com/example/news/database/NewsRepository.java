package com.example.news.database;

import com.example.news.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
    Iterable<News> findAllByOrderByDate();
    boolean existsById(long id);
}
