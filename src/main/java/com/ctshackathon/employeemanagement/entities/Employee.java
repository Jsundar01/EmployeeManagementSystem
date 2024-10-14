package com.ctshackathon.employeemanagement.entities;

import com.ctshackathon.employeemanagement.dto.EmployeeDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "employees")
@Setter
@Getter
@ToString
public class Employee {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @NotNull
    private Department department;

    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotEmpty(message = "Phone cannot be empty")
    private String phone;

    @NotNull(message = "Address cannot be empty")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Address address;

    @NotEmpty
    private String designation;

    @NotNull
    private Long salary;

    @NotEmpty
    private String location;

    public Employee() {
    }

    public Employee(String name, Department department, String email, String phone, Address address) {
        this.name = name;
        this.department = department;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Employee(EmployeeDTO employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.email = employee.getEmail();
        this.phone = employee.getPhone();
        this.address = new Address(employee.getAddress());
        this.department = new Department(employee.getDepartment());
        this.designation = employee.getDesignation();
        this.salary = employee.getSalary();
        this.location = employee.getLocation();
    }

    public void update(EmployeeDTO employee) {
        if (employee.getName() != null) {
            this.name = employee.getName();
        }
        if (employee.getDepartment() != null) {
            this.department = new Department(employee.getDepartment());
        }
        if (employee.getEmail() != null) {
            this.email = employee.getEmail();
        }
        if (employee.getPhone() != null) {
            this.phone = employee.getPhone();
        }
        if (employee.getAddress() != null) {
            this.address = new Address(employee.getAddress());
        }
        if (employee.getDesignation() != null) {
            this.designation = employee.getDesignation();
        }
        if (employee.getSalary() != null) {
            this.salary = employee.getSalary();
        }
        if (employee.getLocation() != null) {
            this.location = employee.getLocation();
        }
    }
}
