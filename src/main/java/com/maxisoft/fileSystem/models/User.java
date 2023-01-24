package com.maxisoft.fileSystem.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Username should not be empty")
    @Column(name = "username")
    private String username;

    @NotEmpty(message = "Password should not be empty")
    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    @NotEmpty(message = "User firstName should not be empty")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "User lastName should not be empty")
    private String lastName;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Event> events = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
}
