package com.example.demo.domain;

import java.util.List;
import com.example.demo.converter.StringListConverter;
import javax.persistence.*;

@Entity
@Table(name = "permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer permissionId;

    @Column(unique = true, nullable = false, length = 50)
    private String permissionName;

    @Lob
    private String description;

    @Column(name = "allowed_endpoints", columnDefinition = "TEXT")
    @Convert(converter = StringListConverter.class)
    private List<String> allowedEndpoints;

    @Column(name = "is_system")
    private Boolean isSystem = false;

    // Getters and setters
    // Constructor(s)
}
