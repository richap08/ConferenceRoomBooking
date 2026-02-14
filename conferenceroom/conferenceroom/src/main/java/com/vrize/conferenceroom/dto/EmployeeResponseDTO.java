package com.vrize.conferenceroom.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String employeeCode;

    private DepartmentDTO department;
}
