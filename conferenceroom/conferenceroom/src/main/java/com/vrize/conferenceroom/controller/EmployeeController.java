package com.vrize.conferenceroom.controller;

import com.vrize.conferenceroom.dto.EmployeeRequestDTO;
import com.vrize.conferenceroom.dto.EmployeeResponseDTO;
import com.vrize.conferenceroom.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public EmployeeResponseDTO getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeResponseDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/test-import")
    public String testExternalApi() {
        employeeService.fetchExternalEmployees();
        return "External API called successfully. Check console.";
    }

    @PostMapping
    public EmployeeResponseDTO createEmployee(@RequestBody EmployeeRequestDTO dto) {
        return employeeService.createEmployee(dto);
    }
}