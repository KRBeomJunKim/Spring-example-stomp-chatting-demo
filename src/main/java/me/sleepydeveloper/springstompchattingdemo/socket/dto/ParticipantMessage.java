package me.sleepydeveloper.springstompchattingdemo.socket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ParticipantMessage {
    private final ParticipantMessageType type;
    private final String username;
}
