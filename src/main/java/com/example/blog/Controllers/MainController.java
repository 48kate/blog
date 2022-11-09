package com.example.blog.Controllers;

import com.example.blog.Models.Article;
import com.example.blog.Services.AccountService;
import com.example.blog.repo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller //отвечвет за обработку всех переходов на сайте
public class MainController {
 //обрабатывает адрес главной страницы
 @Autowired
 private ArticleRepository ArticleRepository;

 @GetMapping("/")
 public String home(Model model) {
     //model.addAttribute("title", "Главная страница");
     // массив данных, который будет содержать все записи их таблицы article:
     Iterable<Article> articles = ArticleRepository.findAll();
     //передаем эти записи в шаблон:
     model.addAttribute("articles", articles);
     return "home";
 }

}
