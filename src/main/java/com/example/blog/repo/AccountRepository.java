package com.example.blog.repo;

import com.example.blog.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
     Account findByUsername (String username);
     }

