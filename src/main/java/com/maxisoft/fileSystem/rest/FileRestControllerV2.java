package com.maxisoft.fileSystem.rest;

import com.maxisoft.fileSystem.dto.FileDTO;
import com.maxisoft.fileSystem.exceptions.FileNotCreatedExceptions;
import com.maxisoft.fileSystem.exceptions.FileNotFoundExceptions;
import com.maxisoft.fileSystem.models.File;
import com.maxisoft.fileSystem.models.Status;
import com.maxisoft.fileSystem.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/FileSystem/V2/files")
public class FileRestControllerV2 {
    private final FileService fileService;

    @Autowired
    public FileRestControllerV2(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public ResponseEntity<List<File>> getAllFiles() {
        List<File> files = fileService.getAll();
        if (files.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(files, HttpStatus.OK);
    }

    @GetMapping("/{file_id}")
    public ResponseEntity<FileDTO> getFileById(@PathVariable("file_id") Long fileId) {
        if (fileId == null) throw new FileNotFoundExceptions("File with ID: " + fileId + " not exists");

        File file = fileService.getById(fileId);
        FileDTO fileDTO = FileDTO.convertFileToDTO(file);
        if (fileDTO == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(fileDTO, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<File> saveFile(@RequestBody @Valid FileDTO fileDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new FileNotCreatedExceptions(errorMsg.toString());
        }

        if (fileDTO == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        File file = FileDTO.convertDtoToFile(fileDTO);
        fileService.save(file);

        return new ResponseEntity<>(file, HttpStatus.CREATED);
    }

    @PutMapping("/{file_id}/update")
    public ResponseEntity<File> updateFile(@PathVariable("file_id") Long fileId, @RequestBody @Valid FileDTO fileDTO, BindingResult bindingResult) {
        if (fileId == null) throw new FileNotFoundExceptions("File with ID: " + fileId + " not exists");
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new FileNotFoundExceptions(errorMsg.toString());
        }

        File file = FileDTO.convertDtoToFile(fileDTO);
        file.setId(fileId);
        fileService.update(file);

        return new ResponseEntity<>(file, HttpStatus.OK);
    }

    @DeleteMapping("/{file_id}/delete")
    public ResponseEntity<File> deleteFile(@PathVariable("file_id") Long fileId) {
        if (fileId == null) throw new FileNotFoundExceptions("File with ID: " + fileId + " not exists");

        File file = fileService.getById(fileId);
        if (file == null) throw new FileNotFoundExceptions("File with ID: " + fileId + " not exists");

        file.setStatus(Status.DELETED);
        fileService.delete(fileId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler
    private ResponseEntity<FileNotFoundExceptions> handleException(FileNotFoundExceptions exception) {
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<FileNotCreatedExceptions> handleException(FileNotCreatedExceptions exception) {
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
}


