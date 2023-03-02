package com.example.blog.repo;
import com.example.blog.Models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


//репозиторий для модели Article
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByTitleOrAuthorIgnoreCase (String title, String author);
    @Query("SELECT a FROM Article a WHERE a.category = :category")
    List<Article> findByCategory(String category);
}
