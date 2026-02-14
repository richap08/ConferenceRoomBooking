package com.vrize.conferenceroom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponseDTO {

    private Long id;
    private String name;
    private Integer capacity;
    private String location;

    private List<EquipmentResponseDTO> equipment;
}