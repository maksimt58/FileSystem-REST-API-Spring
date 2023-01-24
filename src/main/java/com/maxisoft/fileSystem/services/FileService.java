package com.maxisoft.fileSystem.services;

import com.maxisoft.fileSystem.models.File;
import com.maxisoft.fileSystem.models.Status;
import com.maxisoft.fileSystem.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FileService implements GenericService<File, Long> {
    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public List<File> getAll() {
        return fileRepository.findAll();
    }

    @Override
    public File getById(Long id) {
        return fileRepository.getReferenceById(id);
    }

    @Override
    @Transactional
    public File save(File file) {
        if(file.getStatus() == null){
            file.setStatus(Status.NEW);
        }
        return fileRepository.save(file);
    }

    @Override
    @Transactional
    public File update(File file) {
        if(file.getStatus() == null || !file.getStatus().equals(Status.UPDATED)){
            file.setStatus(Status.UPDATED);
        }
        return fileRepository.save(file);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        fileRepository.deleteById(id);
    }

}
