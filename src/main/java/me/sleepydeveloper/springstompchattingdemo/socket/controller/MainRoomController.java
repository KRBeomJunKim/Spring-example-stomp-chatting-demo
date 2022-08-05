package me.sleepydeveloper.springstompchattingdemo.socket.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sleepydeveloper.springstompchattingdemo.domain.account.Account;
import me.sleepydeveloper.springstompchattingdemo.domain.chattingroom.service.ChattingRoomService;
import me.sleepydeveloper.springstompchattingdemo.security.AccountDetails;
import me.sleepydeveloper.springstompchattingdemo.socket.dto.ParticipantMessage;
import me.sleepydeveloper.springstompchattingdemo.socket.dto.ParticipantMessageType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainRoomController {

    private final ChattingRoomService chattingRoomService;

    @MessageMapping("/chatting/main/participant/arrive")
    @SendTo("/topic/chatting/main/participants")
    public ParticipantMessage newParticipantsArrive(@AuthenticationPrincipal AccountDetails accountDetails) {
        Account account = accountDetails.getAccount();
        chattingRoomService.addParticipantToMainRoom(account);
        log.info("Application : new participant arrived, username : {}", account.getUsername());
        return new ParticipantMessage(ParticipantMessageType.ARRIVE, account.getUsername());
    }

    @MessageMapping("/chatting/main/participant/leave")
    @SendTo("/topic/chatting/main/participants")
    public ParticipantMessage newParticipantsLeave(@AuthenticationPrincipal AccountDetails accountDetails) {
        Account account = accountDetails.getAccount();
        chattingRoomService.removeParticipantToMainRoom(account);
        log.info("Application : participant laved, username : {}", account.getUsername());
        return new ParticipantMessage(ParticipantMessageType.LEAVE, account.getUsername());
    }

}
