package me.sleepydeveloper.springstompchattingdemo.socket.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sleepydeveloper.springstompchattingdemo.domain.account.Account;
import me.sleepydeveloper.springstompchattingdemo.domain.chatting.Chatting;
import me.sleepydeveloper.springstompchattingdemo.domain.chatting.ChattingService;
import me.sleepydeveloper.springstompchattingdemo.security.AccountDetails;
import me.sleepydeveloper.springstompchattingdemo.socket.dto.ChattingMessageRequest;
import me.sleepydeveloper.springstompchattingdemo.socket.dto.ChattingMessageResponse;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChattingController {

    private final ChattingService chattingService;

    @MessageMapping("/chatting/room/{roomId}/chatting")
    public ChattingMessageResponse sendChatting(
            @Payload ChattingMessageRequest request,
            @AuthenticationPrincipal AccountDetails accountDetails,
            @DestinationVariable Long roomId
    ) {
        Account account = accountDetails.getAccount();
        Chatting chatting = chattingService.sendChatting(roomId, account.getId(), request.getMessage());

        log.info("Chatting : room={}, account={}, message={}", roomId, account.getId(), chatting.getMessage());

        return ChattingMessageResponse.builder()
                .id(chatting.getId())
                .username(chatting.getAccount().getUsername())
                .message(chatting.getMessage())
                .createdDate(chatting.getCreatedDate())
                .build();
    }


}
