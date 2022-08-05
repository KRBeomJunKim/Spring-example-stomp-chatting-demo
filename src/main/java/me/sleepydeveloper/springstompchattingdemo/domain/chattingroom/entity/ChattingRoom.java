package me.sleepydeveloper.springstompchattingdemo.domain.chattingroom.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.sleepydeveloper.springstompchattingdemo.domain.account.Account;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor @Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class ChattingRoom {

    @Id
    @GeneratedValue
    @Column(name = "chatting_room_id")
    private Long id;

    @ManyToMany
    @JoinTable(name = "account_chatting_room",
            joinColumns = @JoinColumn(name = "chatting_room_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id"))
    Set<Account> participants = new HashSet<>();

}
