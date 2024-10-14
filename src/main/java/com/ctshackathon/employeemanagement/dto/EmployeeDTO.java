package com.ctshackathon.employeemanagement.dto;

import com.ctshackathon.employeemanagement.entities.Employee;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private AddressDTO address;
    private DepartmentDTO department;
    private String designation;
    private Long salary;
    private String location;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.email = employee.getEmail();
        this.phone = employee.getPhone();
        this.address = new AddressDTO(employee.getAddress());
        this.department = new DepartmentDTO(employee.getDepartment());
        this.designation = employee.getDesignation();
        this.salary = employee.getSalary();
        this.location = employee.getLocation();
    }

}
