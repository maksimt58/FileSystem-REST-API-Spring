package com.maxisoft.fileSystem.dto;

import com.maxisoft.fileSystem.models.Event;
import com.maxisoft.fileSystem.models.File;
import com.maxisoft.fileSystem.models.Status;
import org.modelmapper.ModelMapper;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EventDTO {
    @NotNull
    private Long id;
    @NotNull
    @NotEmpty(message = "Event name should not be empty")
    private String name;
    @NotNull
    @NotEmpty(message = "Event's userId should not be empty")
    private Long userId;
    @NotNull
    @NotEmpty(message = "Event's file should not be empty")
    private File file;
    private Status status;

    public EventDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static Event convertDtoToEvent(EventDTO eventDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(eventDTO, Event.class);
    }

    public static EventDTO convertEventToDTO(Event event) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(event, EventDTO.class);
    }
}
