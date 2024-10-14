package com.ctshackathon.employeemanagement.dto;

import com.ctshackathon.employeemanagement.entities.Department;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class DepartmentDTO {
    private Long id;
    private String name;

    public DepartmentDTO() {
    }

    public DepartmentDTO(Long id, String name, String location) {
        this.id = id;
        this.name = name;
    }

    public DepartmentDTO(Department department) {
        this.id = department.getId();
        this.name = department.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentDTO that = (DepartmentDTO) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
