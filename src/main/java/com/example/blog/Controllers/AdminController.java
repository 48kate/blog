package com.example.blog.Controllers;

import com.example.blog.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", accountService.allUsers());
        return "admin";
    }

    @GetMapping("/admin/{id}")
    public String  deleteUser(@PathVariable Long id) {
        accountService.deleteUser(id);
        return "redirect:/admin";
    }

}
