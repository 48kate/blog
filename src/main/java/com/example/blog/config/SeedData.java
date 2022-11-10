package com.example.blog.config;

import com.example.blog.Models.Account;
import com.example.blog.Models.Article;
import com.example.blog.Models.Role;
import com.example.blog.Services.AccountService;
import com.example.blog.Services.ArticleService;
import com.example.blog.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*public class SeedData implements CommandLineRunner {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Article> article = articleService.getAll();

        if (article.size() == 0) {

            Role user = new Role();
            user.setName("ROLE_USER");
            roleRepository.save(user);

            Role admin = new Role();
            admin.setName("ROLE_ADMIN");
            roleRepository.save(admin);

            Account account1 = new Account();
            Account account2 = new Account();

            account1.setFirstName("user_first");
            account1.setLastName("user_last");
            account1.setUsername("user.user@domain.com");
            account1.setPassword("password");
            Set<Role> roles1 = new HashSet<>();
            roleRepository.findById("ROLE_USER").ifPresent(roles1::add);
            account1.setRoles(roles1);


            account2.setFirstName("admin_first");
            account2.setLastName("admin_last");
            account2.setUsername("admin.admin@domain.com");
            account2.setPassword("password");

            Set<Role> roles2 = new HashSet<>();
            roleRepository.findById("ROLE_ADMIN").ifPresent(roles2::add);
            roleRepository.findById("ROLE_USER").ifPresent(roles2::add);
            account2.setRoles(roles2);

            accountService.saveUser(account1);
            accountService.saveUser(account2);

        }
    }

} */
