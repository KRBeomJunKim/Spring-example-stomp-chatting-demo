package me.sleepydeveloper.springstompchattingdemo.socket.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ChattingMessageResponse {

    private Long id;

    private String username;

    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createdDate;

    @Builder
    public ChattingMessageResponse(Long id, String username, String message, LocalDateTime createdDate) {
        this.id = id;
        this.username = username;
        this.message = message;
        this.createdDate = createdDate;
    }
}
