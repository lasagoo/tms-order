package com.linkinsense.tmsorder.shared;

public interface DomainEventPublisher {
    public void publish(Object event);
}
