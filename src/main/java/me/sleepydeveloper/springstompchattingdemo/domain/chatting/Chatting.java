package me.sleepydeveloper.springstompchattingdemo.domain.chatting;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.sleepydeveloper.springstompchattingdemo.domain.account.Account;
import me.sleepydeveloper.springstompchattingdemo.domain.chattingroom.entity.ChattingRoom;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter
@NoArgsConstructor
public class Chatting {

    @Id @GeneratedValue
    @Column(name = "chatting_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private ChattingRoom chattingRoom;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private String message;

    private LocalDateTime createdDate;

    @Builder
    public Chatting(ChattingRoom chattingRoom, Account account, String message, LocalDateTime createdDate) {
        this.createdDate = createdDate;
        this.chattingRoom = chattingRoom;
        this.account = account;
        this.message = message;
    }

}
