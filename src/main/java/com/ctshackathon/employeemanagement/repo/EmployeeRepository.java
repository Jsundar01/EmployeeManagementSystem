package com.ctshackathon.employeemanagement.repo;

import com.ctshackathon.employeemanagement.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
