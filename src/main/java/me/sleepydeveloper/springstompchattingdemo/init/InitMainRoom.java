package me.sleepydeveloper.springstompchattingdemo.init;

import lombok.RequiredArgsConstructor;
import me.sleepydeveloper.springstompchattingdemo.domain.chattingroom.repository.ChattingRoomRepository;
import me.sleepydeveloper.springstompchattingdemo.domain.chattingroom.entity.MainChattingRoom;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitMainRoom {

    private final ChattingRoomRepository chattingRoomRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initMainRoom() {
        chattingRoomRepository.save(new MainChattingRoom());
    }

}
