package me.sleepydeveloper.springstompchattingdemo.socket.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sleepydeveloper.springstompchattingdemo.domain.account.Account;
import me.sleepydeveloper.springstompchattingdemo.domain.chattingroom.service.ChattingRoomService;
import me.sleepydeveloper.springstompchattingdemo.security.AccountDetails;
import me.sleepydeveloper.springstompchattingdemo.socket.dto.ParticipantMessage;
import me.sleepydeveloper.springstompchattingdemo.socket.dto.ParticipantMessageType;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserRoomController {
    private final ChattingRoomService chattingRoomService;

    @MessageMapping("/chatting/room/{roomId}/arrive")
    @SendTo("/topic/chatting/room/{roomId}/participants")
    public ParticipantMessage newParticipantsArrive(@AuthenticationPrincipal AccountDetails accountDetails,
                                                    @DestinationVariable Long roomId) {
        Account account = accountDetails.getAccount();
        chattingRoomService.participate(roomId, account);
        log.info("Application : room:{}, username : {} arrived", roomId, account.getUsername());
        return new ParticipantMessage(ParticipantMessageType.ARRIVE, account.getUsername());
    }

    @MessageMapping("/chatting/room/{roomId}/leave")
    @SendTo("/topic/chatting/room/{roomId}/participants")
    public ParticipantMessage newParticipantsLeave(@AuthenticationPrincipal AccountDetails accountDetails,
                                                    @DestinationVariable Long roomId) {
        Account account = accountDetails.getAccount();
        chattingRoomService.leave(roomId, account);
        log.info("Application : room:{}, username : {} leave", roomId, account.getUsername());
        return new ParticipantMessage(ParticipantMessageType.LEAVE, account.getUsername());
    }
}
