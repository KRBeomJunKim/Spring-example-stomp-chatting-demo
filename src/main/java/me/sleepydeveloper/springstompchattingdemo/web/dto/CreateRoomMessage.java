package me.sleepydeveloper.springstompchattingdemo.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.sleepydeveloper.springstompchattingdemo.socket.dto.RoomMessageType;

@AllArgsConstructor
@Getter
public class CreateRoomMessage {

    private final RoomMessageType type;
    private final Long roomId;
    private final String roomName;
    private final String ownerName;

}
