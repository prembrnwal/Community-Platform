package com.example.Community.Platform.DTO.Jamming_Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ParticipantResponse {

    private Long userId;
    private String username;
    private LocalDateTime joinedAt;

}
