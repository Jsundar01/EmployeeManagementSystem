package com.ctshackathon.employeemanagement.entities;

import com.ctshackathon.employeemanagement.dto.DepartmentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Department() {
    }

    public Department(DepartmentDTO department) {
        this.id = department.getId();
        this.name = department.getName();
    }
}
