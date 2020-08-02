package com.randymcbride.conferencedemo.models;

import javax.persistence.*;

@Entity(name="ticket_prices")
public class TicketPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ticket_price_id")
    private Long ticketPriceId;

    @Column(name="ticket_type_code")
    private String ticketTypeCode;

    @Column(name="pricing_category_code")
    private String pricingCategoryCode;

    @Column(name="base_price")
    private Double basePrice;

    public Long getTicketPriceId() {
        return ticketPriceId;
    }

    public void setTicketPriceId(Long ticketPriceId) {
        this.ticketPriceId = ticketPriceId;
    }

    public String getTicketTypeCode() {
        return ticketTypeCode;
    }

    public void setTicketTypeCode(String ticketTypeCode) {
        this.ticketTypeCode = ticketTypeCode;
    }

    public String getPricingCategoryCode() {
        return pricingCategoryCode;
    }

    public void setPricingCategoryCode(String pricingCategoryCode) {
        this.pricingCategoryCode = pricingCategoryCode;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }
}
