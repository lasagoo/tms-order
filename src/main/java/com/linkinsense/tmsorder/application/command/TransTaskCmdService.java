package com.linkinsense.tmsorder.application.command;

import com.linkinsense.tmsorder.application.result.Result;

public interface TransTaskCmdService {
    public <T> Result<T> planTransTask(Long transTaskId);
}
