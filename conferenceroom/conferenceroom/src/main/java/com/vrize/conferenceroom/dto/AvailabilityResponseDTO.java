package com.vrize.conferenceroom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvailabilityResponseDTO {

    private Long roomId;
    private boolean available;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
