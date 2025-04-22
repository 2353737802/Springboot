package com.example.demo.domain;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.example.demo.converter.IntegerListConverter;
import javax.persistence.*;
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupId;

    @Column(unique = true, nullable = false, length = 50)
    private String groupName;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private GroupType type;

    @ManyToOne
    @JoinColumn(name = "parent_group_id")
    private Group parentGroup;

    @Column(name = "permission_template", columnDefinition = "TEXT")
    @Convert(converter = IntegerListConverter.class)
    private List<Integer> permissionTemplate;

    @Column(name = "is_system")
    private Boolean isSystem = false;

    @ManyToMany(mappedBy = "groups")
    private Set<User> users = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "group_classification",
        joinColumns = @JoinColumn(name = "group_id"),
        inverseJoinColumns = @JoinColumn(name = "classification_id"))
    private Set<GroupClassification> classifications = new HashSet<>();

    // Enum definition
    public enum GroupType { DEPARTMENT, ROLE }

    // Getters and setters
    // Constructor(s)
}
