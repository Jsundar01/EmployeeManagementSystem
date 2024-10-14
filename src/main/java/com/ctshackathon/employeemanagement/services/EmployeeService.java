package com.ctshackathon.employeemanagement.services;

import com.ctshackathon.employeemanagement.dto.BasicEmployeeDTO;
import com.ctshackathon.employeemanagement.dto.DepartmentDTO;
import com.ctshackathon.employeemanagement.dto.EmployeeDTO;
import com.ctshackathon.employeemanagement.entities.Department;
import com.ctshackathon.employeemanagement.entities.Employee;
import com.ctshackathon.employeemanagement.repo.DepartmentRepository;
import com.ctshackathon.employeemanagement.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public EmployeeDTO addEmployee(EmployeeDTO employee) {
        // Add employee to database
        departmentRepository.findByName(employee.getDepartment().getName())
                .ifPresent(department -> employee.setDepartment(new DepartmentDTO(department)));
        Employee saved = employeeRepository.save(new Employee(employee));
        System.out.println("Saved employee: " + saved);
        return new EmployeeDTO(saved);
    }

    public EmployeeDTO getEmployeeById(Long id) {
        // Get employee from database
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return null;
        }
        return new EmployeeDTO(employee);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employee) {
        // Update employee in database
        Employee employeeEntity = employeeRepository.findById(id).orElse(null);
        if (employeeEntity == null) {
            return null;
        }
        employeeEntity.update(employee);
        return new EmployeeDTO(employeeRepository.save(employeeEntity));
    }

    public boolean deleteEmployee(Long id) {
        // Delete employee from database
        if (!employeeRepository.existsById(id)) {
            return false;
        }
        employeeRepository.deleteById(id);
        return true;
    }

    public List<EmployeeDTO> listAllEmployees() {
        // List all employees from database
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDtos = new ArrayList<>(employees.size());
        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = new EmployeeDTO(employee);
            employeeDtos.add(employeeDTO);
        }
        return employeeDtos;
    }

    public List<BasicEmployeeDTO> listAllEmployeesBasicDetails() {
        List<EmployeeDTO> employees = listAllEmployees();
        List<BasicEmployeeDTO> basicEmployeeDTOS = new ArrayList<>(employees.size());
        for (EmployeeDTO employee : employees) {
            basicEmployeeDTOS.add(new BasicEmployeeDTO(employee));
        }
        return basicEmployeeDTOS;
    }

}
