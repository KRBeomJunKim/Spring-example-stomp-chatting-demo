package me.sleepydeveloper.springstompchattingdemo.web;

import lombok.RequiredArgsConstructor;
import me.sleepydeveloper.springstompchattingdemo.domain.account.Account;
import me.sleepydeveloper.springstompchattingdemo.domain.chatting.Chatting;
import me.sleepydeveloper.springstompchattingdemo.domain.chatting.ChattingService;
import me.sleepydeveloper.springstompchattingdemo.domain.chattingroom.entity.ChattingRoom;
import me.sleepydeveloper.springstompchattingdemo.domain.chattingroom.entity.UserChattingRoom;
import me.sleepydeveloper.springstompchattingdemo.domain.chattingroom.service.ChattingRoomService;
import me.sleepydeveloper.springstompchattingdemo.security.AccountDetails;
import me.sleepydeveloper.springstompchattingdemo.socket.dto.RoomMessageType;
import me.sleepydeveloper.springstompchattingdemo.web.dto.CreateRoomMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChattingRoomController {

    private final ChattingRoomService chattingRoomService;
    private final ChattingService chattingService;
    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping("/chatting/room/create")
    public String createChattingRoom() {
        return "/chatting/create";
    }

    @PostMapping("/chatting/room/create")
    public String createChattingRoomPost(@AuthenticationPrincipal AccountDetails accountDetails,
            @RequestParam String name) {
        Account account = accountDetails.getAccount();
        UserChattingRoom chattingRoom = (UserChattingRoom) chattingRoomService.save(new UserChattingRoom(name, account));
        messagingTemplate.convertAndSend("/topic/chatting/rooms", new CreateRoomMessage(RoomMessageType.CREATED, chattingRoom.getId(), chattingRoom.getName(), account.getUsername()));
        return "redirect:/home";
    }

    @GetMapping("/chatting/room/{roomId}")
    public String getChattingRoom(
            @AuthenticationPrincipal AccountDetails accountDetails,
            @PathVariable Long roomId,
            Model model
            ) {
        model.addAttribute("account", accountDetails.getAccount());
        model.addAttribute("room", (UserChattingRoom)chattingRoomService.findById(roomId));
        model.addAttribute("chattings", chattingService.find(roomId));
        return "chatting/room";
    }
}
