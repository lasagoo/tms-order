package com.linkinsense.tmsorder.application.command;


import com.linkinsense.tmsorder.application.command.cmd.ClientOrderCheckCmd;
import com.linkinsense.tmsorder.application.command.cmd.ClientOrderUpdateCmd;
import com.linkinsense.tmsorder.application.command.cmd.OrderDetailAddCmd;
import com.linkinsense.tmsorder.application.command.cmd.ClientOrderCreateCmd;
import com.linkinsense.tmsorder.application.result.Result;
import com.linkinsense.tmsorder.domain.aggregate.order.TransTaskPlanEvent;
import com.linkinsense.tmsorder.domain.aggregate.transport.TransOrderCreateEvent;

public interface ClientOrderCmdService {
    public<T> Result<T> createOrder(ClientOrderCreateCmd cmd);
    public<T> Result<T> deleteOrder(Long orderId);
    public<T> Result<T> updateOrder(ClientOrderUpdateCmd cmd);

    public<T> Result<T> checkOrder(ClientOrderCheckCmd clientOrderCheckCmd);

    void handleTransTaskPlanEvent(TransTaskPlanEvent transTaskPlanEvent);
    void handleTransOrderCreateEvent(TransOrderCreateEvent transOrderCreateEvent);
}
