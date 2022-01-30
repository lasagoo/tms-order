package com.linkinsense.tmsorder.application.command;


import com.linkinsense.tmsorder.application.command.cmd.CreateClientOrderCommand;
import com.linkinsense.tmsorder.application.result.Result;

public interface ClientOrderCmdService {
    public<T> Result<T> createOrder(CreateClientOrderCommand cmd);
    public<T> Result<T> deleteOrder(Long orderId);
}
