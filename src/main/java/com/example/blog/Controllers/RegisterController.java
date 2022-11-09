package com.example.blog.Controllers;

import com.example.blog.Models.Account;
import com.example.blog.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/register")
    public String register(Model model) {
            Account account = new Account();
            model.addAttribute("accountForm", account);
            return "register";
    }
    @PostMapping("/register")
    public String registerNewUser (@ModelAttribute ("accountForm") Account accountForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        if (!accountForm.getPassword().equals(accountForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "register";
        }
        if (!accountService.saveUser(accountForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "register";
        }
        return "redirect:/";
    }

}

