package com.randymcbride.conferencedemo.services;

import com.randymcbride.conferencedemo.models.Attendee;
import com.randymcbride.conferencedemo.models.AttendeeTicket;
import com.randymcbride.conferencedemo.models.PricingCategory;
import com.randymcbride.conferencedemo.models.TicketPrice;
import com.randymcbride.conferencedemo.repositories.AttendeeRepository;
import com.randymcbride.conferencedemo.repositories.AttendeeTicketRepository;
import com.randymcbride.conferencedemo.repositories.PricingCategoryRepository;
import com.randymcbride.conferencedemo.repositories.TicketPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class TicketPurchaseService {
    private final AttendeeTicketRepository attendeeTicketRepository;
    private final PricingCategoryRepository pricingCategoryRepository;
    private final TicketPriceRepository ticketPriceRepository;
    private final AttendeeRepository attendeeRepository;

    @Autowired
    public TicketPurchaseService(AttendeeTicketRepository attendeeTicketRepository,
                                 AttendeeRepository attendeeRepository,
                                 PricingCategoryRepository pricingCategoryRepository,
                                 TicketPriceRepository ticketPriceRepository) {
        this.attendeeTicketRepository = attendeeTicketRepository;
        this.attendeeRepository = attendeeRepository;
        this.pricingCategoryRepository = pricingCategoryRepository;
        this.ticketPriceRepository = ticketPriceRepository;
    }

    public AttendeeTicket purchaseTicket(Long attendeeId, LocalDate purchaseDate) {
        Attendee attendee = attendeeRepository
                .findById(attendeeId)
                .orElseThrow(() -> new IllegalArgumentException("There is no attendee with id " + 1));

        TicketPrice ticketPrice = getTicketPriceByPurchaseDate(purchaseDate);

        AttendeeTicket ticket = new AttendeeTicket();
        ticket.setAttendee(attendee);
        ticket.setTicketPrice(ticketPrice);
        ticket.setNetPrice(ticketPrice.getBasePrice());

        return attendeeTicketRepository.saveAndFlush(ticket);
    }

    private TicketPrice getTicketPriceByPurchaseDate(LocalDate purchaseDate) {
        Date purchaseDateValue = java.sql.Date.valueOf(purchaseDate);

        List<PricingCategory> pricingCategories = pricingCategoryRepository.findAll();

        PricingCategory pricingCategory = pricingCategories.stream()
                .filter(category -> category.includesDate(purchaseDateValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("There are no tickets available for purchase on the date provided."));

        List<TicketPrice> ticketPricesByCode = ticketPriceRepository.findAllByPricingCategoryCode(pricingCategory.getCode());

        return ticketPricesByCode
                .stream()
                .filter(category -> category.getTicketTypeCode().equals("S"))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("There is no ticket price for category with code " + pricingCategory.getCode()));
    }
}
