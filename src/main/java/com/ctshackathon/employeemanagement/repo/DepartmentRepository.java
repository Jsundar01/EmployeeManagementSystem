package com.ctshackathon.employeemanagement.repo;

import com.ctshackathon.employeemanagement.dto.DepartmentDTO;
import com.ctshackathon.employeemanagement.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByName(String name);
}
