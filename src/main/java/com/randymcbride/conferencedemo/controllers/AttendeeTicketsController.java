package com.randymcbride.conferencedemo.controllers;

import com.randymcbride.conferencedemo.models.AttendeeTicket;
import com.randymcbride.conferencedemo.repositories.AttendeeRepository;
import com.randymcbride.conferencedemo.repositories.AttendeeTicketRepository;
import com.randymcbride.conferencedemo.services.TicketPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attendees/{attendeeId}/tickets")
public class AttendeeTicketsController {
    private final AttendeeTicketRepository attendeeTicketRepository;
    private final AttendeeRepository attendeeRepository;
    private TicketPurchaseService ticketPurchaseService;

    @Autowired
    public AttendeeTicketsController(
            AttendeeTicketRepository attendeeTicketRepository,
            AttendeeRepository attendeeRepository,
            TicketPurchaseService ticketPurchaseService
    ) {
        this.attendeeTicketRepository = attendeeTicketRepository;
        this.attendeeRepository = attendeeRepository;
        this.ticketPurchaseService = ticketPurchaseService;
    }

    @GetMapping
    public List<AttendeeTicket> list(@PathVariable Long attendeeId) {
        return attendeeTicketRepository.findByAttendeeAttendeeId(attendeeId);
    }

    @PostMapping
    public AttendeeTicket create(@PathVariable Long attendeeId,
                                @RequestParam("purchaseDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate purchaseDate
    ) {
        return ticketPurchaseService.purchaseTicket(attendeeId, purchaseDate);
    }
}
