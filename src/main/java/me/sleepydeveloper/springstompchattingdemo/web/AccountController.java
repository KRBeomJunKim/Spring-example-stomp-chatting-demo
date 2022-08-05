package me.sleepydeveloper.springstompchattingdemo.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sleepydeveloper.springstompchattingdemo.domain.account.Account;
import me.sleepydeveloper.springstompchattingdemo.domain.account.AccountService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    private final AccountService accountService;

    @GetMapping("/sign-up")
    public String signUp() {
        return "account/signup";
    }

    @PostMapping("/sign-up")
    public String signUpPost(@RequestParam String username,
                             @RequestParam String password) {
        accountService.save(new Account(username, passwordEncoder.encode(password)));
        log.info("Application : Success Sign Up with (username) " + username);
        return "redirect:/home";
    }

}
