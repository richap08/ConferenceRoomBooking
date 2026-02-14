package com.vrize.conferenceroom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String type;
}
