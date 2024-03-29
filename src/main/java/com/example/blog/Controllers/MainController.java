package com.example.blog.Controllers;

import com.example.blog.Models.Article;
import com.example.blog.Services.AccountService;
import com.example.blog.Services.ArticleService;
import com.example.blog.repo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;


@Controller //отвечает за обработку всех переходов на сайте
public class MainController {
    @PersistenceContext
    private EntityManager entityManager;
    //обрабатывает адрес главной страницы
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/")
    public String home(Model model) {
        // массив данных, который будет содержать все записи их таблицы article:
        Iterable<Article> articles = articleRepository.findAll();
        //передаем эти записи в шаблон:
        model.addAttribute("articles", articles);
        return "home";
    }

    @PostMapping("/search")
    public String search(@RequestParam String search, Map<String, Object> model) {
        List<Article> articles;
        model.put("search", search);
                if (search != null && !search.isEmpty()) {
            articles = articleRepository.findByTitleOrAuthorIgnoreCase(search, search);
            model.put("articles", articles);
            if (articles == null || articles.isEmpty()) {
                return "search_error";
            } else {
                return "search";
            }
        } else {
            return "search_error";
        }
    }
    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public String category(Model model, @RequestParam String category) {
        System.out.println(1);
        Query q = entityManager.createNativeQuery("SELECT * FROM article WHERE category = " + category, Article.class);
        System.out.println(2);
        List<Article> articles = (List<Article>) q.getResultList();
        System.out.println(3);
        model.addAttribute("articles", articles);
        System.out.println(4);
        return "home";
    }

}


