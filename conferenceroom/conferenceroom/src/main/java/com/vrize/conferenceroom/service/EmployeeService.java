package com.vrize.conferenceroom.service;

import com.vrize.conferenceroom.dto.EmployeeRequestDTO;
import com.vrize.conferenceroom.dto.EmployeeResponseDTO;
import com.vrize.conferenceroom.dto.ExternalEmployeeDTO;
import com.vrize.conferenceroom.entity.Department;
import com.vrize.conferenceroom.entity.Employee;
import com.vrize.conferenceroom.exception.ResourceNotFoundException;
import com.vrize.conferenceroom.repository.DepartmentRepository;
import com.vrize.conferenceroom.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;

    // =====================================================
    // ✅ GET EMPLOYEE BY ID
    // =====================================================
    public EmployeeResponseDTO getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        return modelMapper.map(employee, EmployeeResponseDTO.class);
    }

    // =====================================================
    // ✅ GET ALL EMPLOYEES
    // =====================================================
    public List<EmployeeResponseDTO> getAllEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(emp -> modelMapper.map(emp, EmployeeResponseDTO.class))
                .collect(Collectors.toList());
    }

    // =====================================================
    // ✅ IMPORT EMPLOYEES FROM EXTERNAL API
    // =====================================================
    @Transactional
    public void fetchExternalEmployees() {

        String url = "https://jsonplaceholder.typicode.com/users";

        ExternalEmployeeDTO[] externalEmployees =
                restTemplate.getForObject(url, ExternalEmployeeDTO[].class);

        if (externalEmployees == null) return;

        // Default department (make sure ID=1 exists)
        Department department = departmentRepository.findById(1L)
                .orElseThrow(() -> new ResourceNotFoundException("Default Department not found"));

        for (ExternalEmployeeDTO dto : externalEmployees) {

            if (dto.getEmail() == null) continue;

            // Generate employee code dynamically
            String generatedCode = "EXT-" + Math.abs(dto.getEmail().hashCode());

            // Skip duplicates
            if (employeeRepository.findByEmployeeCode(generatedCode).isPresent()) {
                continue;
            }

            // Map DTO → Entity
            Employee employee = modelMapper.map(dto, Employee.class);

            // Set business fields
            employee.setEmployeeCode(generatedCode);
            employee.setDepartment(department);

            employeeRepository.save(employee);
        }
    }

    // =====================================================
    // ✅ MANUAL EMPLOYEE CREATION
    // =====================================================
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto) {

        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        Employee employee = modelMapper.map(dto, Employee.class);
        employee.setDepartment(department);

        Employee saved = employeeRepository.save(employee);

        return modelMapper.map(saved, EmployeeResponseDTO.class);
    }
}