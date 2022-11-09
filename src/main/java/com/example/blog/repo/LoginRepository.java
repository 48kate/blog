package com.example.blog.repo;

import com.example.blog.Models.Login;
import org.springframework.data.repository.CrudRepository;

public interface LoginRepository extends CrudRepository<Login, Long> {
}
