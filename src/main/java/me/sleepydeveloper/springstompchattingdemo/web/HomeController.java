package me.sleepydeveloper.springstompchattingdemo.web;

import lombok.RequiredArgsConstructor;
import me.sleepydeveloper.springstompchattingdemo.domain.chattingroom.entity.ChattingRoom;
import me.sleepydeveloper.springstompchattingdemo.domain.chattingroom.entity.UserChattingRoom;
import me.sleepydeveloper.springstompchattingdemo.domain.chattingroom.repository.ChattingRoomRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ChattingRoomRepository chattingRoomRepository;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/home")
    public String home(Model model) {
        ChattingRoom chattingRoom = chattingRoomRepository.findMainRoom()
                .orElseThrow(() -> new IllegalArgumentException("main room을 찾을 수 없습니다."));
        List<UserChattingRoom> userRooms = chattingRoomRepository.findUserRooms();
        model.addAttribute("mainRoom", chattingRoom);
        model.addAttribute("userRooms", userRooms);
        return "home";
    }

}
