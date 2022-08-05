package me.sleepydeveloper.springstompchattingdemo.domain.chattingroom.repository;

import me.sleepydeveloper.springstompchattingdemo.domain.chattingroom.entity.ChattingRoom;
import me.sleepydeveloper.springstompchattingdemo.domain.chattingroom.entity.MainChattingRoom;
import me.sleepydeveloper.springstompchattingdemo.domain.chattingroom.entity.UserChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Long> {
    @Query("select cr from ChattingRoom cr where type(cr)=MainChattingRoom")
    Optional<MainChattingRoom> findMainRoom();

    @Query("select cr from ChattingRoom cr where type(cr)=UserChattingRoom")
    List<UserChattingRoom> findUserRooms();

    @Query("select cr from ChattingRoom cr left join fetch cr.participants where cr.id=:id")
    Optional<MainChattingRoom> findByIdWithParticipants(Long id);
}
