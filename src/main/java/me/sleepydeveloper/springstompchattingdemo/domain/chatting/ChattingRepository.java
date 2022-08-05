package me.sleepydeveloper.springstompchattingdemo.domain.chatting;

import me.sleepydeveloper.springstompchattingdemo.domain.chattingroom.entity.ChattingRoom;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChattingRepository extends JpaRepository<Chatting, Long> {

    List<Chatting> findByChattingRoom(ChattingRoom chattingRoom);

}
