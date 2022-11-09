package com.example.blog.Controllers;

import com.example.blog.Models.Account;
import com.example.blog.Models.Article;
import com.example.blog.Services.AccountService;
import com.example.blog.Services.ArticleService;
import com.example.blog.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String Add(Model model, Principal principal) {

        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }
     //используем accountService для поиска юзернэйма
        Optional<Account> optionalAccount = Optional.ofNullable(accountRepository.findByUsername(authUsername));
        //если аккаунт с таким юзернэймом сущ-ет, то создаем новую статью и присваиваем ей этот аккаунт
        if (optionalAccount.isPresent()) {
            Article article = new Article();
            article.setAccount(optionalAccount.get());
            model.addAttribute("article", article);
            return "add";
        } else {
            return "redirect:/";
        }

    }
    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String PostAdd(@ModelAttribute Article article, Principal principal) {
        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }
        if (article.getAccount().getUsername().compareToIgnoreCase(authUsername) < 0) {
            // TODO: some kind of error?
            // our account email on the Post not equal to current logged in account!
        }
        articleService.save(article);
        return "redirect:/home/" + article.getId();
    }
    @GetMapping("/home/{id}")
    public String Details(@PathVariable long id, Model model) {
        // ищем статью по id
        Optional<Article> optionalArticle = this.articleService.getById(id);

        // если пост существует, поместить его в модель
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            model.addAttribute("article", article);
            return "details";
        } else {
            return "redirect:/home/";
        }
    }
    @PostMapping("/home/{id}")
    @PreAuthorize("isAuthenticated()")
    public String updatePost(@PathVariable Long id, Article article, BindingResult result, Model model) {

            Optional<Article> optionalArticle = articleService.getById(id);
            if (optionalArticle.isPresent()) {
                Article existingArticle = optionalArticle.get();
                existingArticle.setTitle(article.getTitle());
                existingArticle.setAuthor(article.getAuthor());
                existingArticle.setFull_text(article.getFull_text());


                articleService.save(existingArticle);
            }

            return "redirect:/home/" + article.getId();
        }

    @GetMapping("/home/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String getPostForEdit(@PathVariable Long id, Model model) {

        // find post by id
        Optional<Article> optionalArticle = articleService.getById(id);
        // if post exist put it in model
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            model.addAttribute("article", article);
            return "article_edit";
        } else {
            return "redirect:/home/";
        }
    }
    @GetMapping("/home/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deletePost(@PathVariable Long id) {

        // find post by id
        Optional<Article> optionalArticle = articleService.getById(id);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();

            articleService.delete(article);
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }

}


/*    public String Details(@PathVariable(value = "id") long id, Model model) {
        if (!ArticleRepository.existsById(id)){
            return "redirect:/";
        }
        Optional<Article> article = ArticleRepository.findById(id);
        ArrayList<Article> res = new ArrayList<>();
        article.ifPresent(res::add);
        model.addAttribute("article", res);
        return "details"; */
/*public String Add(Model model) {
    Article article = new Article();
    model.addAttribute("article", article);
    return "add";*/