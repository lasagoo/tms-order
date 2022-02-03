package com.linkinsense.tmsorder.infrastructure.event.consumer;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.linkinsense.tmsorder.application.command.ClientOrderCmdService;
import com.linkinsense.tmsorder.domain.aggregate.order.ClientOrder;
import com.linkinsense.tmsorder.domain.aggregate.task.TransTaskPlanEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TransTaskListener {
    @Autowired
    private ClientOrderCmdService clientOrderCmdService;
    @Autowired
    private EventBus eventBus;

    @PostConstruct
    public void init(){
        eventBus.register(this);
    }
    @Subscribe
    public void handlePlaningEvent(TransTaskPlanEvent transTaskPlanEvent){
        clientOrderCmdService.handleTransTaskPlanEvent(transTaskPlanEvent);
    }
}
