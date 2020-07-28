package com.randymcbride.conferencedemo.repositories;

import com.randymcbride.conferencedemo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
