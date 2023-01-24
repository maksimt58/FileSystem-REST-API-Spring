package com.maxisoft.fileSystem.rest;

import com.maxisoft.fileSystem.dto.EventDTO;
import com.maxisoft.fileSystem.models.Event;
import com.maxisoft.fileSystem.models.Status;
import com.maxisoft.fileSystem.services.EventService;
import com.maxisoft.fileSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/FileSystem/V2/events")
public class EventRestControllerV2 {
    private final EventService eventService;
    private final UserService userService;

    @Autowired
    public EventRestControllerV2(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        List<EventDTO> eventsDTO = eventService.getAll().stream().map(EventDTO::convertEventToDTO).collect(Collectors.toList());

        if (eventsDTO.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(eventsDTO, HttpStatus.OK);
    }

    @GetMapping("/{event_id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable("event_id") Long eventId) {
        Event event = eventService.getById(eventId);
        EventDTO eventDTO = EventDTO.convertEventToDTO(event);
        return new ResponseEntity<>(eventDTO, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<EventDTO> saveEvent(@RequestBody @Valid EventDTO eventDTO, BindingResult bindingResult) {
        Event event = EventDTO.convertDtoToEvent(eventDTO);
        eventService.save(event);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{event_id}/update")
    public ResponseEntity<Event> updateEvent(@PathVariable("event_id") Long eventId, @RequestBody @Valid Event event, BindingResult bindingResult) {
        event.setId(eventId);
        eventService.update(event);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{event_id}/delete")
    public ResponseEntity<Event> deleteEvent(@PathVariable("event_id") Long eventId) {
        Event event = eventService.getById(eventId);
        event.setStatus(Status.DELETED);
        eventService.delete(eventId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
