package com.vrize.conferenceroom.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequestDTO {

    private String name;
    private String email;
    private String employeeCode;
    private Long departmentId;
}
