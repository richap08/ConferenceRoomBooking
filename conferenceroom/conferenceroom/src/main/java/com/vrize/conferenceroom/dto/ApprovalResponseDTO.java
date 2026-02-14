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
public class ApprovalResponseDTO {

    private Long id;
    private LocalDateTime approvalTime;
    private String remarks;

    private EmployeeResponseDTO approvedBy;
}
