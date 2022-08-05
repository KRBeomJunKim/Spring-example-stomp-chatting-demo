package me.sleepydeveloper.springstompchattingdemo.domain.chatting;

import lombok.RequiredArgsConstructor;
import me.sleepydeveloper.springstompchattingdemo.domain.account.Account;
import me.sleepydeveloper.springstompchattingdemo.domain.account.AccountRepository;
import me.sleepydeveloper.springstompchattingdemo.domain.chattingroom.entity.ChattingRoom;
import me.sleepydeveloper.springstompchattingdemo.domain.chattingroom.repository.ChattingRoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChattingService {

    private final ChattingRoomRepository roomRepository;
    private final ChattingRepository chattingRepository;
    private final AccountRepository accountRepository;

    public List<Chatting> find(Long roomId) {
        ChattingRoom chattingRoom = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("room을 찾을 수 없습니다."));
        return chattingRepository.findByChattingRoom(chattingRoom);
    }

    public Chatting sendChatting(Long roomId, Long accountId, String message) {
        ChattingRoom chattingRoom = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("room을 찾을 수 없습니다."));

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("account를 찾을 수 없습니다."));

        return chattingRepository.save(Chatting.builder()
                .account(account)
                .chattingRoom(chattingRoom)
                .message(message)
                .createdDate(LocalDateTime.now())
                .build());
    }
}
