package com.vrize.conferenceroom.service;

import com.vrize.conferenceroom.dto.EquipmentRequestDTO;
import com.vrize.conferenceroom.dto.EquipmentResponseDTO;
import com.vrize.conferenceroom.entity.Equipment;
import com.vrize.conferenceroom.entity.Room;
import com.vrize.conferenceroom.exception.ResourceNotFoundException;
import com.vrize.conferenceroom.repository.EquipmentRepository;
import com.vrize.conferenceroom.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    public EquipmentResponseDTO addEquipment(Long roomId, EquipmentRequestDTO dto) {

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));

        Equipment equipment = modelMapper.map(dto, Equipment.class);
        equipment.setRoom(room);

        Equipment saved = equipmentRepository.save(equipment);

        return modelMapper.map(saved, EquipmentResponseDTO.class);
    }
}