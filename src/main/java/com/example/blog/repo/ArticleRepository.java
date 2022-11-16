package com.example.blog.repo;

import com.example.blog.Models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;


//репозиторий для модели Article
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByTitleOrAuthorIgnoreCase (String title, String author);
    @Query("SELECT a FROM Article a WHERE a.category = :category")
    List<Article> findByCategory(String category);


  /*  @Query("SELECT a FROM Article a WHERE " +
        "a.author LIKE CONCAT ('%',:query, '%')" +
        "Or a.title LIKE CONCAT ('%',:query, '%')")
    List<Article> searchArticle (String query); */

    //native query
  /*  @Query("SELECT * FROM article a WHERE " +
            "a.author LIKE CONCAT ('%',:query, '%')" +
            "Or a.title LIKE CONCAT ('%',:query, '%')"), nativeQuery = true
    List<Article> searchSQL (String query);*/


}
