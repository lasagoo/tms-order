package com.linkinsense.tmsorder.application.command;

import com.linkinsense.tmsorder.application.result.Result;

public interface TransTaskCmdService {
    //生成任务单
    public <T> Result<T> genTransTask(Long orderId, Long transTaskId);
}
