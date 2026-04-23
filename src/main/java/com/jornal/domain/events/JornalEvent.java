package com.jornal.domain.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;
import java.time.LocalDateTime;

@Getter
public class JornalEvent extends ApplicationEvent {

    private final LocalDateTime ocorridoEm;

    public JornalEvent(Object source) {
        super(source);
        this.ocorridoEm = LocalDateTime.now();
    }
}
