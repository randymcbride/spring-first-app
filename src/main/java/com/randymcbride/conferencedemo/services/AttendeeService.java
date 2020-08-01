package com.randymcbride.conferencedemo.services;

import com.randymcbride.conferencedemo.models.Attendee;
import com.randymcbride.conferencedemo.repositories.AttendeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class AttendeeService {
    private AttendeeRepository attendeeRepository;

    @Autowired
    public AttendeeService(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    public Attendee create(Attendee attendee) {
        assertIsUniqueAttendee(attendee.getEmail());
        return attendeeRepository.saveAndFlush(attendee);
    }

    public Attendee update(Long id, Attendee attendee) {
        Attendee existingAttendee = attendeeRepository.getOne(id);

        if(attendee.getEmail() != existingAttendee.getEmail()){
            assertIsUniqueAttendee(attendee.getEmail());
        }
        BeanUtils.copyProperties(attendee, existingAttendee, "attendeeId");
        return attendeeRepository.saveAndFlush(existingAttendee);
    }

    private void assertIsUniqueAttendee(String email) {
        if(attendeeRepository.existsByEmail(email)) {
            throw new DataIntegrityViolationException("The email " + email + " is already registered");
        }
    }
}

