package com.linkinsense.tmsorder.infrastructure.event;

import com.google.common.eventbus.EventBus;
import com.linkinsense.tmsorder.shared.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GuavaDomainEventPublisher implements DomainEventPublisher {
    @Autowired
    EventBus eventBus;

    @Override
    public void publish(Object event) {
        eventBus.post(event);
    }
}
