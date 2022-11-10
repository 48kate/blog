package com.example.blog.Services;

import com.example.blog.Models.Account;
import com.example.blog.Models.Role;
import com.example.blog.repo.AccountRepository;
import com.example.blog.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

//Содержит методы для бизнес-логики приложения, реализует интерфейс UserDetailsService
@Service
public class AccountService implements UserDetailsService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }

        return account;
    }

    public Account findUserById(Long accountId) {
        Optional<Account> accountFromDb = accountRepository.findById(accountId);
        return accountFromDb.orElse(new Account());
    }

    public List<Account> allUsers() {
        return accountRepository.findAll();
    }

    public boolean saveUser(Account account) {
        Account accountFromDB = accountRepository.findByUsername(account.getUsername());

        if (accountFromDB != null) {
            return false;
        }
        account.setId(account.getId());
        account.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return true;
    }

    public boolean deleteUser(Long accountId) {
        if (accountRepository.findById(accountId).isPresent()) {
            accountRepository.deleteById(accountId);
            return true;
        }
        return false;
    }

    public List<Account> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM Account u WHERE u.id > :paramId", Account.class)
                .setParameter("paramId", idMin).getResultList();
    }
}


