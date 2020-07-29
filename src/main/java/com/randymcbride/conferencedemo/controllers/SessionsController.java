package com.randymcbride.conferencedemo.controllers;

import com.randymcbride.conferencedemo.models.Session;
import com.randymcbride.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {
    private SessionRepository sessionRepository;

    @Autowired
    public SessionsController(SessionRepository sessionRepository){
        this.sessionRepository = sessionRepository;
    }

    @GetMapping
    public List<Session> list(){
        return sessionRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id){

        return sessionRepository.getOne(id);
    }

    @PostMapping
    public Session create(@RequestBody final Session session){
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(value="{id}", method =RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value="{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody Session session){
        Session existingSession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(session, existingSession, "sessionId");
        return sessionRepository.saveAndFlush(existingSession);
    }
}
