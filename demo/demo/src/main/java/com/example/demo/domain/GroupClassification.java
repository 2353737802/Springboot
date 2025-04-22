package com.example.demo.domain;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
@Entity
@Table(name = "group_classifications")
public class GroupClassification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer classificationId;

    @Column(unique = true, nullable = false, length = 50)
    private String classificationName;

    @ManyToOne
    @JoinColumn(name = "parent_classification_id")
    private GroupClassification parentClassification;

    @Lob
    private String description;

    @ManyToMany(mappedBy = "classifications")
    private Set<Group> groups = new HashSet<>();

    // Getters and setters
    // Constructor(s)
}