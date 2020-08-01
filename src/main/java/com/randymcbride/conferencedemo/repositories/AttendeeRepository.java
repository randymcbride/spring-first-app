package com.randymcbride.conferencedemo.repositories;

import com.randymcbride.conferencedemo.models.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
    boolean existsByEmail(String email);

}
