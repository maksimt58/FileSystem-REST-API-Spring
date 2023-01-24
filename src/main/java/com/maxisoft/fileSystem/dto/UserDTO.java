package com.maxisoft.fileSystem.dto;

import com.maxisoft.fileSystem.models.Status;
import com.maxisoft.fileSystem.models.User;
import org.modelmapper.ModelMapper;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDTO {
    @NotNull
    private Long id;
    @NotNull
    @NotEmpty(message = "User firstName should not be empty")
    private String firstName;
    @NotNull
    @NotEmpty(message = "User lastName should not be empty")
    private String lastName;
    private Status status;

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static UserDTO convertUserToDTO(User user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDTO.class);
    }

    public static User convertDtoTotUser(UserDTO userDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userDTO, User.class);
    }
}
