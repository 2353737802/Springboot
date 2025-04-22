package com.example.demo.domain;
//import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(length = 10)
    private String gender;

    @Column(nullable = false, length = 255)
    private String passwordHash;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(unique = true, nullable = false, length = 20)
    private String phone;

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToMany
    @JoinTable(
        name = "user_permissions",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "user_group",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<Group> groups = new HashSet<>();

    // Getters and Setters
    public enum UserRole { USER, ADMIN }
}
