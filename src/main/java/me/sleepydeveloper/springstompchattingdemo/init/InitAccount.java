package me.sleepydeveloper.springstompchattingdemo.init;

import lombok.RequiredArgsConstructor;
import me.sleepydeveloper.springstompchattingdemo.domain.account.Account;
import me.sleepydeveloper.springstompchattingdemo.domain.account.AccountService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitAccount {

    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @EventListener(ApplicationReadyEvent.class)
    public void initAccount() {
        accountService.save(new Account("user", passwordEncoder.encode("1111")));
        accountService.save(new Account("user2", passwordEncoder.encode("1111")));
        accountService.save(new Account("user3", passwordEncoder.encode("1111")));
    }

}
