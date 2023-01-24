package com.maxisoft.fileSystem.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Data
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "event_name")
    @NotEmpty(message = "Event name should not be empty")
    private String name;
    @ManyToOne
    @JoinColumn (name = "file_id")
    private File file;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
}
