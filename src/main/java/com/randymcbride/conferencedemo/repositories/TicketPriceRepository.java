package com.randymcbride.conferencedemo.repositories;

import com.randymcbride.conferencedemo.models.TicketPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

@Repository
public interface TicketPriceRepository extends JpaRepository<TicketPrice, Long> {
    Optional<TicketPrice> findByPricingCategoryCode(String code);

    List<TicketPrice> findAllByPricingCategoryCode(String code);
}
