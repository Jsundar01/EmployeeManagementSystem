package com.ctshackathon.employeemanagement.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @NotNull(message = "Dept cannot be empty")
    private Department department;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role cannot be empty")
    private Role role;

    public User(String username, String encode, Department dept, Role role) {
        this.username = username;
        this.password = encode;
        this.department = dept;
        this.role = role;
    }

}