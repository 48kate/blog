package com.example.blog.Controllers;
import com.example.blog.Models.Account;
import com.example.blog.Models.Article;
import com.example.blog.Services.AccountService;
import com.example.blog.Services.ArticleService;
import com.example.blog.repo.AccountRepository;
import com.example.blog.repo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.security.Principal;
import java.util.Optional;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String Add(Model model, Principal principal) {

        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }
        Optional<Account> optionalAccount = Optional.ofNullable(accountRepository.findByUsername(authUsername));
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
    public ResponseEntity<Article> createArticle(@RequestBody Article article)  {
        Article saved = articleRepository.save(article);
        return new ResponseEntity<>(saved, prepareHeader(saved.getId()), HttpStatus.CREATED);
    }
    @GetMapping("/home/{id}")
    public ResponseEntity<Optional<Article>> findArticle(@PathVariable Long id)  {
        return ResponseEntity.ok(articleService.getById(id));
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
                existingArticle.setCategory(article.getCategory());


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
    @GetMapping("/home/{id}/remove")
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
    private HttpHeaders prepareHeader (Long id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        responseHeaders.setLocation(uri);
        return responseHeaders;
    }
}

