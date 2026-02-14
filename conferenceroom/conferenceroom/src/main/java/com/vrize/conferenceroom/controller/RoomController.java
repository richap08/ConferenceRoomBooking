package com.vrize.conferenceroom.controller;

import com.vrize.conferenceroom.dto.AvailabilityResponseDTO;
import com.vrize.conferenceroom.dto.RoomRequestDTO;
import com.vrize.conferenceroom.dto.RoomResponseDTO;
import com.vrize.conferenceroom.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public RoomResponseDTO createRoom(@RequestBody RoomRequestDTO dto) {
        return roomService.createRoom(dto);
    }

    @GetMapping("/{id}/availability")
    public AvailabilityResponseDTO checkAvailability(
            @PathVariable Long id,
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {

        return roomService.checkAvailability(id, start, end);
    }
}
