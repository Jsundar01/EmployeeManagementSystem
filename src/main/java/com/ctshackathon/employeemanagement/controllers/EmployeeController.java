package com.ctshackathon.employeemanagement.controllers;

import com.ctshackathon.employeemanagement.dto.DepartmentDTO;
import com.ctshackathon.employeemanagement.dto.EmployeeDTO;
import com.ctshackathon.employeemanagement.entities.Role;
import com.ctshackathon.employeemanagement.entities.User;
import com.ctshackathon.employeemanagement.repo.UserRepository;
import com.ctshackathon.employeemanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserRepository userRepo;

    @PostMapping
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeDTO employee, Principal principal) {
        System.out.println("Received employee add request");
        System.out.println(employee);
        String name = principal.getName();
        System.out.println("Principal name = " + name);
        User user = userRepo.findByUsername(name).get();
        Role role = user.getRole();
        if (role == Role.SUPER_HR || (role == Role.HR && employee.getDepartment()
                .equals(new DepartmentDTO(user.getDepartment())))) {
            return ResponseEntity.ok(employeeService.addEmployee(employee));
        } else {
            return ResponseEntity.status(403).build();
        }
    }



    @GetMapping
    public ResponseEntity<?> listAllEmployees() {
        System.out.println("Received employee list request");
        return ResponseEntity.ok(employeeService.listAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id, Principal principal) {
        System.out.println("Received employee get request");
        String name = principal.getName();
        System.out.println("Principal name = " + name);
        User user = userRepo.findByUsername(name).get();
        Role role = user.getRole();
        EmployeeDTO employeeById = employeeService.getEmployeeById(id);
        if (employeeById == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        if (role == Role.SUPER_HR || (role == Role.HR && employeeById.getDepartment()
                .equals(new DepartmentDTO(user.getDepartment())))) {
            return ResponseEntity.ok(employeeById);
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employee, Principal principal) {
        System.out.println("Received employee update request");
        String name = principal.getName();
        System.out.println("Principal name = " + name);
        User user = userRepo.findByUsername(name).get();
        Role role = user.getRole();
        if (role == Role.SUPER_HR || (role == Role.HR && employee.getDepartment()
                .equals(new DepartmentDTO(user.getDepartment())))) {
            EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employee);
            if (updatedEmployee == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedEmployee);
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id, Principal principal) {
        System.out.println("Received employee delete request");
        String name = principal.getName();
        System.out.println("Principal name = " + name);
        User user = userRepo.findByUsername(name).get();
        Role role = user.getRole();
        if (role == Role.SUPER_HR || (role == Role.HR && employeeService.getEmployeeById(id).getDepartment()
                .equals(new DepartmentDTO(user.getDepartment())))) {
            if (!employeeService.deleteEmployee(id)) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(403).build();
        }
    }

}
