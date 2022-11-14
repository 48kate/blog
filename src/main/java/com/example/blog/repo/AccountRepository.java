package com.example.blog.repo;

import com.example.blog.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
     Account findByUsername (String username);
     /*@Query(value = "SELECT c FROM Account c WHERE c.title LIKE '%' || :keyword || '%'"
             + " OR c.author LIKE '%' || :keyword || '%'"
             + " OR c.full_text LIKE '%' || :keyword || '%'")
     public List<Account> search(@Param("keyword") String keyword);*/
     }

