package com.ctshackathon.employeemanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BasicEmployeeDTO {
    private Long id;
    private String name;
    private DepartmentDTO department;
    private String designation;

    public BasicEmployeeDTO(EmployeeDTO employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.department = employee.getDepartment();
        this.designation = employee.getDesignation();
    }
}
