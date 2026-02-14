package com.vrize.conferenceroom.controller;

import com.vrize.conferenceroom.dto.EquipmentRequestDTO;
import com.vrize.conferenceroom.dto.EquipmentResponseDTO;
import com.vrize.conferenceroom.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;

    @PostMapping("/{id}/equipment")
    public EquipmentResponseDTO addEquipment(
            @PathVariable Long id,
            @RequestBody EquipmentRequestDTO dto) {

        return equipmentService.addEquipment(id, dto);
    }
}
