package me.sleepydeveloper.springstompchattingdemo.domain.chattingroom.entity;


import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("main")
@NoArgsConstructor
public class MainChattingRoom extends ChattingRoom{
}
