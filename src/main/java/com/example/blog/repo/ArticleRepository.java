package com.example.blog.repo;

import com.example.blog.Models.Article;
import org.springframework.data.jpa.repository.JpaRepository;


//репозиторий для модели Article
public interface ArticleRepository extends JpaRepository<Article, Long> {

}
