package com.linkinsense.tmsorder.application.command.impl;

import com.linkinsense.tmsorder.application.command.TransTaskCmdService;
import com.linkinsense.tmsorder.application.result.Result;
import com.linkinsense.tmsorder.domain.aggregate.task.TransTask;
import com.linkinsense.tmsorder.domain.aggregate.task.TransTaskPlanEvent;
import com.linkinsense.tmsorder.domain.aggregate.task.TransTaskRepository;
import com.linkinsense.tmsorder.domain.aggregate.task.valueobject.TransTaskStatus;
import com.linkinsense.tmsorder.infrastructure.event.GuavaDomainEventPublisher;
import com.linkinsense.tmsorder.infrastructure.event.consumer.TransTaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class TransTaskCmdServiceImpl implements TransTaskCmdService {
    @Autowired
    private TransTaskRepository transTaskRepository;

    @Autowired
    private GuavaDomainEventPublisher guavaDomainEventPublisher;

    @Override
    public <T> Result<T> planTransTask(Long transTaskId) {
        TransTask transTask = transTaskRepository.find(transTaskId);
        if(transTask == null){
            throw new IllegalArgumentException("transTaskId:" + transTaskId + " not exist");
        }
        if(!transTask.getStatus().nextStatus().equals(TransTaskStatus.SCHEDULED)){
            throw new RuntimeException("trans task cannot change into SCHEDULED status");
        }
        transTask.setStatus(TransTaskStatus.SCHEDULED);
        transTaskRepository.update(transTask);

        //发布任务单状态变化事件
        TransTaskPlanEvent transTaskPlanEvent = new TransTaskPlanEvent(transTask);
        guavaDomainEventPublisher.publish(transTaskPlanEvent);

        return Result.success();
    }
}
