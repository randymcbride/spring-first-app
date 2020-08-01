package com.randymcbride.conferencedemo.repositories;

import com.randymcbride.conferencedemo.models.AttendeeTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendeeTicketRepository extends JpaRepository<AttendeeTicket, Long> {
    List<AttendeeTicket> findByAttendeeAttendeeId(Long attendeeId);
}
