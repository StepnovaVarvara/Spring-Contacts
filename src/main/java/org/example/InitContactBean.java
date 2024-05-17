package org.example;

import lombok.RequiredArgsConstructor;
import org.example.processor.ContactWorker;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
@Profile("init")
public class InitContactBean {

    private final ContactWorker contactWorker;

    @PostConstruct
    public void initContacts() {
        contactWorker.saveInitContacts();
    }
}
