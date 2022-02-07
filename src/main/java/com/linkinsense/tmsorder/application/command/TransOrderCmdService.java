package com.linkinsense.tmsorder.application.command;

import com.linkinsense.tmsorder.application.command.cmd.TransOrderCreateCmd;
import com.linkinsense.tmsorder.application.result.Result;
import com.linkinsense.tmsorder.domain.aggregate.transport.TransOrder;

public interface TransOrderCmdService {

    public<T>Result<T> createTransOrder(TransOrderCreateCmd transOrderCreateCmd);
}
