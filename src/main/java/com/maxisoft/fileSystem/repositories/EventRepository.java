package com.maxisoft.fileSystem.repositories;

import com.maxisoft.fileSystem.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
