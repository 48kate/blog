package com.example.blog.Services;

import com.example.blog.Models.Article;
import com.example.blog.repo.ArticleRepository;
import org.hibernate.sql.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    public Optional<Article> getById(Long id) {
        return articleRepository.findById(id);
    }

    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    public Article save(Article article) {
        return articleRepository.save(article);
    }

    public void delete(Article article) {
        articleRepository.delete(article);
    }

}

