package me.sleepydeveloper.springstompchattingdemo.security;

import lombok.Getter;
import me.sleepydeveloper.springstompchattingdemo.domain.account.Account;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class AccountDetails extends User {

    private final Account account;

    public AccountDetails(Account account) {
        super(account.getUsername(), account.getPassword(), List.of(new SimpleGrantedAuthority("USER")));
        this.account = account;
    }
}
