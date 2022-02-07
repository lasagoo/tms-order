package com.linkinsense.tmsorder.infrastructure.event.consumer;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.linkinsense.tmsorder.application.command.ClientOrderCmdService;
import com.linkinsense.tmsorder.domain.aggregate.order.TransTaskPlanEvent;
import com.linkinsense.tmsorder.domain.aggregate.transport.TransOrderCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TransOrderListener {
    @Autowired
    private ClientOrderCmdService clientOrderCmdService;
    @Autowired
    private EventBus eventBus;

    @PostConstruct
    public void init(){
        eventBus.register(this);
    }
    @Subscribe
    public void handlePlaningEvent(TransOrderCreateEvent transOrderCreateEvent){
        clientOrderCmdService.handleTransOrderCreateEvent(transOrderCreateEvent);
    }
}
