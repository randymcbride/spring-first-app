package com.randymcbride.conferencedemo.controllers;

import com.randymcbride.conferencedemo.models.Attendee;
import com.randymcbride.conferencedemo.models.AttendeeTicket;
import com.randymcbride.conferencedemo.repositories.AttendeeRepository;
import com.randymcbride.conferencedemo.repositories.AttendeeTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/attendees/{attendeeId}/tickets")
public class AttendeeTicketsController {
    private final AttendeeTicketRepository attendeeTicketRepository;
    private final AttendeeRepository attendeeRepository;

    @Autowired
    public AttendeeTicketsController(
            AttendeeTicketRepository attendeeTicketRepository,
            AttendeeRepository attendeeRepository
    ){
        this.attendeeTicketRepository = attendeeTicketRepository;
        this.attendeeRepository = attendeeRepository;
    }

    @GetMapping
    public List<AttendeeTicket> list(@PathVariable Long attendeeId){
        return attendeeTicketRepository.findByAttendeeAttendeeId(attendeeId);
    }

    @PostMapping
    public AttendeeTicket create(@PathVariable Long attendeeId, @RequestBody final AttendeeTicket attendeeTicket){
        Attendee attendee = attendeeRepository.findById(attendeeId)
                .orElseThrow(() -> new IllegalArgumentException("There is no Attendee with id " + attendeeId));
        attendeeTicket.setAttendee(attendee);
        return attendeeTicketRepository.saveAndFlush(attendeeTicket);
    }
}
