package com.vrize.conferenceroom.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalRequestDTO {

    @NotNull
    private Long approvedByEmployeeId;

    private String remarks;
}