package me.sleepydeveloper.springstompchattingdemo.domain.chattingroom.service;

import lombok.RequiredArgsConstructor;
import me.sleepydeveloper.springstompchattingdemo.domain.account.Account;
import me.sleepydeveloper.springstompchattingdemo.domain.chattingroom.entity.ChattingRoom;
import me.sleepydeveloper.springstompchattingdemo.domain.chattingroom.entity.MainChattingRoom;
import me.sleepydeveloper.springstompchattingdemo.domain.chattingroom.repository.ChattingRoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChattingRoomService {

    private final ChattingRoomRepository chattingRoomRepository;

    public void addParticipantToMainRoom(Account account) {
        MainChattingRoom mainChattingRoom = chattingRoomRepository.findMainRoom()
                .orElseThrow(() -> new IllegalArgumentException("main room을 찾을 수 없습니다."));
        if (mainChattingRoom.getParticipants().contains(account)) {
            return;
        }
        mainChattingRoom.getParticipants().add(account);
    }

    public void removeParticipantToMainRoom(Account account) {
        MainChattingRoom mainChattingRoom = chattingRoomRepository.findMainRoom()
                .orElseThrow(() -> new IllegalArgumentException("main room을 찾을 수 없습니다."));
        mainChattingRoom.getParticipants().remove(account);
    }

    public ChattingRoom save(ChattingRoom chattingRoom) {
        return chattingRoomRepository.save(chattingRoom);
    }

    public ChattingRoom findById(Long roomId) {
        return chattingRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("room을 찾을 수 없습니다."));
    }

    public void participate(Long roomId, Account account) {
        ChattingRoom chattingRoom = chattingRoomRepository.findByIdWithParticipants(roomId)
                .orElseThrow(() -> new IllegalArgumentException("room을 찾을 수 없습니다."));
        chattingRoom.getParticipants().add(account);
    }

    public void leave(Long roomId, Account account) {
        ChattingRoom chattingRoom = chattingRoomRepository.findByIdWithParticipants(roomId)
                .orElseThrow(() -> new IllegalArgumentException("room을 찾을 수 없습니다."));
        chattingRoom.getParticipants().remove(account);
    }
}
