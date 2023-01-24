package com.maxisoft.fileSystem.services;

import com.maxisoft.fileSystem.models.Event;
import com.maxisoft.fileSystem.models.Status;
import com.maxisoft.fileSystem.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EventService implements GenericService<Event, Long> {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event getById(Long id) {
        return eventRepository.getReferenceById(id);
    }

    @Override
    @Transactional
    public Event save(Event event) {
        if (event.getStatus() == null) {
            event.setStatus(Status.NEW);
        }
        return eventRepository.save(event);
    }

    @Override
    @Transactional
    public Event update(Event event) {
        if (event.getStatus() == null || !event.getStatus().equals(Status.UPDATED)) {
            event.setStatus(Status.UPDATED);
        }
        return save(event);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        eventRepository.deleteById(id);
    }

}
