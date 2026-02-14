package com.vrize.conferenceroom.service;

import com.vrize.conferenceroom.dto.AvailabilityResponseDTO;
import com.vrize.conferenceroom.dto.RoomRequestDTO;
import com.vrize.conferenceroom.dto.RoomResponseDTO;
import com.vrize.conferenceroom.entity.Room;
import com.vrize.conferenceroom.repository.BookingRepository;
import com.vrize.conferenceroom.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;

    public RoomResponseDTO createRoom(RoomRequestDTO dto) {

        Room room = modelMapper.map(dto, Room.class);
        Room saved = roomRepository.save(room);

        return modelMapper.map(saved, RoomResponseDTO.class);
    }

    public AvailabilityResponseDTO checkAvailability(
            Long roomId,
            LocalDateTime start,
            LocalDateTime end) {

        boolean conflict = bookingRepository.existsConflictingBooking(roomId, start, end);

        AvailabilityResponseDTO response = new AvailabilityResponseDTO();
        response.setRoomId(roomId);
        response.setAvailable(!conflict);
        response.setStartTime(start);
        response.setEndTime(end);

        return response;
    }
}