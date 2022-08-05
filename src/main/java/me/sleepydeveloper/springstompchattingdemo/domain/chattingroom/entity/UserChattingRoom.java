package me.sleepydeveloper.springstompchattingdemo.domain.chattingroom.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import me.sleepydeveloper.springstompchattingdemo.domain.account.Account;

import javax.persistence.*;

@Entity
@DiscriminatorValue("user")
@NoArgsConstructor @Getter
public class UserChattingRoom extends ChattingRoom{

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public UserChattingRoom(String name, Account account) {
        this.name = name;
        this.account = account;
    }
}
