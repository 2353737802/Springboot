package com.example.demo.domain;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.example.demo.converter.JsonConverter;
import javax.persistence.*;
@Entity
@Table(name = "action_logs")
public class ActionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 100)
    private String action;

    @Column(updatable = false)
    private LocalDateTime timestamp;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = JsonConverter.class)
    private Map<String, Object> details;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Result result;

    @PrePersist
    protected void onCreate() {
        timestamp = LocalDateTime.now();
    }

    public enum Result { SUCCESS, FAILURE }

    // Getters and setters
    // Constructor(s)
}