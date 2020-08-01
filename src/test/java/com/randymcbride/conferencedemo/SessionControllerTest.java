package com.randymcbride.conferencedemo;

import com.randymcbride.conferencedemo.controllers.SessionsController;
import com.randymcbride.conferencedemo.models.Session;
import com.randymcbride.conferencedemo.repositories.SessionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SessionControllerTest {
    @Mock
    SessionRepository sessionRepository;

    @InjectMocks
    SessionsController sessionsController;

    @Test
    public void findsAll(){
        Session session1 = new Session();
        Session session2 = new Session();
        Session session3 = new Session();
        session1.setSessionId(1L);
        session2.setSessionId(2L);
        session3.setSessionId(3L);

        List<Session> sessions = new ArrayList<>();
        sessions.add(session1);
        sessions.add(session2);
        sessions.add(session3);

        when(sessionRepository.findAll()).thenReturn(sessions);
        List<Session> allSessions = sessionsController.list();
        assertEquals(3, allSessions.size());
    }

}
