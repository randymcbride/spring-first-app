package com.randymcbride.conferencedemo.controllers;

import com.randymcbride.conferencedemo.models.Attendee;
import com.randymcbride.conferencedemo.repositories.AttendeeRepository;
import com.randymcbride.conferencedemo.services.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attendees")
public class AttendeeController {
    private final AttendeeRepository attendeeRepository;
    private final AttendeeService attendeeService;

    @Autowired
    public AttendeeController(
            AttendeeRepository attendeeRepository,
            AttendeeService attendeeService
    ) {
        this.attendeeRepository = attendeeRepository;
        this.attendeeService = attendeeService;
    }

    @GetMapping
    public List<Attendee> list() {
        return attendeeRepository.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Attendee get(@PathVariable Long id) {
        return attendeeRepository.getOne(id);
    }

    @PostMapping
    public Attendee create(@RequestBody final Attendee attendee) {
        return attendeeService.create(attendee);
    }

    @RequestMapping(value="{id}", method = RequestMethod.PUT)
    public Attendee update(@PathVariable Long id, @RequestBody final Attendee attendee){
        return attendeeService.update(id, attendee);
    }
}
