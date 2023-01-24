package com.maxisoft.fileSystem.models;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "file_name")
    @NotEmpty(message = "File name should not be empty")
    @NotNull
    private String fileName;
    @Column(name = "file_path")
    @NotEmpty(message = "File path should not be empty")
    @NotNull
    private String path;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

}
