package com.linkinsense.tmsorder.application.command.impl;

import com.linkinsense.tmsorder.application.command.TransTaskCmdService;
import com.linkinsense.tmsorder.application.result.Result;
import com.linkinsense.tmsorder.domain.aggregate.order.ClientOrder;
import com.linkinsense.tmsorder.domain.aggregate.order.ClientOrderRepository;
import com.linkinsense.tmsorder.domain.aggregate.order.TransTask;
import com.linkinsense.tmsorder.domain.aggregate.order.TransTaskPlanEvent;
import com.linkinsense.tmsorder.domain.aggregate.order.TransTaskRepository;
import com.linkinsense.tmsorder.domain.aggregate.order.valueobject.ClientOrderStatus;
import com.linkinsense.tmsorder.domain.aggregate.order.valueobject.TransTaskStatus;
import com.linkinsense.tmsorder.domain.service.ClientOrderDomainService;
import com.linkinsense.tmsorder.infrastructure.event.GuavaDomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@Service
public class TransTaskCmdServiceImpl implements TransTaskCmdService {
    @Autowired
    private TransTaskRepository transTaskRepository;
    @Autowired
    private ClientOrderRepository clientOrderRepository;
    @Autowired
    private ClientOrderDomainService clientOrderDomainService;

    @Autowired
    private GuavaDomainEventPublisher guavaDomainEventPublisher;

    @Transactional
    @Override
    public <T> Result<T> genTransTask(@NotNull Long orderId, @NotNull Long transTaskId) {

        //通过聚合根操作任务单
        ClientOrder clientOrder = clientOrderRepository.find(orderId);
        if(clientOrder == null){
            throw new IllegalArgumentException("orderId:" + orderId + " not exist");
        }

        TransTask transTask = clientOrder.findTransTask(transTaskId);
        if(transTask == null){
            throw new IllegalArgumentException("transTaskId:" + transTaskId + " not exist in clientOrder");
        }

        //检查任务单状态是否允许更新
        if(clientOrderDomainService.mayAcceptTaskStatus(clientOrder,transTask,TransTaskStatus.PREPARED) == Boolean.FALSE){
            throw new IllegalArgumentException("new transTask status " + TransTaskStatus.PREPARED.getValue() + "+ not allowed");
        }

        transTask.setStatus(TransTaskStatus.PREPARED);
        transTaskRepository.update(transTask);

        clientOrder.setStatus(ClientOrderStatus.PREPARED);
        clientOrderRepository.update(clientOrder);

        //发布任务单状态变化事件
        TransTaskPlanEvent transTaskPlanEvent = new TransTaskPlanEvent(transTask);
        guavaDomainEventPublisher.publish(transTaskPlanEvent);

        return Result.success();
    }
}
