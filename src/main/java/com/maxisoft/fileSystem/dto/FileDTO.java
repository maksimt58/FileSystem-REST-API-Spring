package com.maxisoft.fileSystem.dto;

import com.maxisoft.fileSystem.models.File;
import com.maxisoft.fileSystem.models.Status;
import org.modelmapper.ModelMapper;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class FileDTO {
    @NotEmpty(message = "File name should not be empty")
    @NotNull
    private String fileName;
    @NotEmpty(message = "File path should not be empty")
    @NotNull
    private String path;
    private Status status;

    public FileDTO() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static File convertDtoToFile(FileDTO fileDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(fileDTO, File.class);
    }

    public static FileDTO convertFileToDTO(File file) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(file, FileDTO.class);
    }
}
