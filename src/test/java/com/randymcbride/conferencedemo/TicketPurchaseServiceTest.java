package com.randymcbride.conferencedemo;

import com.randymcbride.conferencedemo.models.Attendee;
import com.randymcbride.conferencedemo.models.AttendeeTicket;
import com.randymcbride.conferencedemo.models.PricingCategory;
import com.randymcbride.conferencedemo.models.TicketPrice;
import com.randymcbride.conferencedemo.repositories.AttendeeRepository;
import com.randymcbride.conferencedemo.repositories.AttendeeTicketRepository;
import com.randymcbride.conferencedemo.repositories.PricingCategoryRepository;
import com.randymcbride.conferencedemo.repositories.TicketPriceRepository;
import com.randymcbride.conferencedemo.services.TicketPurchaseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TicketPurchaseServiceTest {
    @Mock
    PricingCategoryRepository pricingCategoryRepository;
    @Mock
    TicketPriceRepository ticketPriceRepository;
    @Mock
    AttendeeRepository attendeeRepository;
    @Mock
    AttendeeTicketRepository attendeeTicketRepository;

    @InjectMocks
    TicketPurchaseService service;

    private TicketPrice earlyBirdPrice;
    private TicketPrice regularPrice;
    private PricingCategory earlyBird;
    private PricingCategory regular;
    private Attendee attendee;
    private ArrayList<PricingCategory> pricingCategories;

    @Before
    public void setup() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        earlyBird = new PricingCategory();
        regular = new PricingCategory();

        earlyBird.setStartDate(dateFormat.parse("2020-01-01"));
        earlyBird.setEndDate(dateFormat.parse("2020-01-31"));
        earlyBird.setCode("E");

        regular.setStartDate(dateFormat.parse("2020-02-01"));
        regular.setEndDate(dateFormat.parse("2020-03-31"));
        regular.setCode("R");

        earlyBirdPrice = new TicketPrice();
        earlyBirdPrice.setBasePrice(100.00);
        earlyBirdPrice.setPricingCategoryCode(earlyBird.getCode());

        pricingCategories = new ArrayList<>();
        pricingCategories.add(earlyBird);
        pricingCategories.add(regular);

        regularPrice = new TicketPrice();
        regularPrice.setBasePrice(150.00);
        regularPrice.setPricingCategoryCode(earlyBird.getCode());

        attendee = new Attendee();
        attendee.setAttendeeId(1L);

        when(pricingCategoryRepository.findAll()).thenReturn(pricingCategories);
        when(ticketPriceRepository.findByPricingCategoryCode("E")).thenReturn(Optional.ofNullable(earlyBirdPrice));
        when(ticketPriceRepository.findByPricingCategoryCode("R")).thenReturn(Optional.ofNullable(regularPrice));
        when(attendeeRepository.findById(1L)).thenReturn(Optional.ofNullable(attendee));
        when(attendeeTicketRepository.saveAndFlush(any(AttendeeTicket.class))).then(returnsFirstArg());
    }

    @Test
    public void purchaseTicket_setsPriceBasedOnDate() {
        AttendeeTicket earlyTicket = service.purchaseTicket(1L, LocalDate.of(2020, 1, 31));
        AttendeeTicket regularTicket = service.purchaseTicket(1L, LocalDate.of(2020, 2, 1));

        assertEquals(earlyBirdPrice.getBasePrice(), earlyTicket.getNetPrice());
        assertEquals(regularPrice.getBasePrice(), regularTicket.getNetPrice());
    }

    @Test(expected = IllegalArgumentException.class)
    public void purchaseTicket_throwsIllegalArgument_whenDateIsOutsideRange(){
        service.purchaseTicket(1L, LocalDate.of(2019, 12, 31));
    }

}
