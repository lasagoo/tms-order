package com.linkinsense.tmsorder.application.command;


import com.linkinsense.tmsorder.application.command.cmd.ClientOrderUpdateCmd;
import com.linkinsense.tmsorder.application.command.cmd.OrderDetailAddCmd;
import com.linkinsense.tmsorder.application.command.cmd.ClientOrderCreateCmd;
import com.linkinsense.tmsorder.application.result.Result;

public interface ClientOrderCmdService {
    public<T> Result<T> createOrder(ClientOrderCreateCmd cmd);
    public<T> Result<T> deleteOrder(Long orderId);
    public<T> Result<T> updateOrder(ClientOrderUpdateCmd cmd);
    public<T> Result<T> addOrderDetail(OrderDetailAddCmd cmd);
}
