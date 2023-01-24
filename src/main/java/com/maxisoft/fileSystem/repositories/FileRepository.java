package com.maxisoft.fileSystem.repositories;

import com.maxisoft.fileSystem.models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

}
