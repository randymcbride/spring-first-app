package com.randymcbride.conferencedemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Currency;

@Entity(name="attendee_tickets")
public class AttendeeTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="attendee_ticket_id")
    private Long attendeeTicketId;

    @ManyToOne
    @JoinColumn(name="attendee_id")
    @JsonIgnore
    private Attendee attendee;

    @ManyToOne
    @JoinColumn(name="ticket_price_id")
    private TicketPrice ticketPrice;

    @ManyToOne
    @JoinColumn(name="discount_code_id")
    private DiscountCode discountCode;

    @Column(name="net_price")
    private Double netPrice;

    public Long getAttendeeTicketId() {
        return attendeeTicketId;
    }

    public void setAttendeeTicketId(Long attendeeTicketId) {
        this.attendeeTicketId = attendeeTicketId;
    }

    public Attendee getAttendee() {
        return attendee;
    }

    public void setAttendee(Attendee attendee) {
        this.attendee = attendee;
    }

    public TicketPrice getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(TicketPrice ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public DiscountCode getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(DiscountCode discountCode) {
        this.discountCode = discountCode;
    }

    public Double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(Double netPrice) {
        this.netPrice = netPrice;
    }
}
