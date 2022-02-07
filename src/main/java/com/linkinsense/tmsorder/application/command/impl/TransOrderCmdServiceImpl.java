package com.linkinsense.tmsorder.application.command.impl;

import com.linkinsense.tmsorder.application.assembler.DtoTransOrderAssembler;
import com.linkinsense.tmsorder.application.command.TransOrderCmdService;
import com.linkinsense.tmsorder.application.command.cmd.TransOrderCreateCmd;
import com.linkinsense.tmsorder.application.result.Result;
import com.linkinsense.tmsorder.domain.aggregate.order.TransTask;
import com.linkinsense.tmsorder.domain.aggregate.order.TransTaskRepository;
import com.linkinsense.tmsorder.domain.aggregate.transport.TransOrder;
import com.linkinsense.tmsorder.domain.aggregate.transport.TransOrderCreateEvent;
import com.linkinsense.tmsorder.domain.aggregate.transport.TransOrderRepository;
import com.linkinsense.tmsorder.domain.service.ClientOrderDomainService;
import com.linkinsense.tmsorder.infrastructure.event.GuavaDomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransOrderCmdServiceImpl implements TransOrderCmdService {
    @Autowired
    DtoTransOrderAssembler dtoTransOrderAssembler;
    @Autowired
    TransOrderRepository transOrderRepository;
    @Autowired
    TransTaskRepository transTaskRepository;
    @Autowired
    ClientOrderDomainService clientOrderDomainService;
    @Autowired
    GuavaDomainEventPublisher guavaDomainEventPublisher;

    @Transactional
    @Override
    public <T> Result<T> createTransOrder(TransOrderCreateCmd transOrderCreateCmd) {

        //还原所有任务单
        List<TransTask> transTasks = transOrderCreateCmd.getTransTasks().stream()
                .map(e->transTaskRepository.find(e.getTaskId()))
                .collect(Collectors.toList());

        //检查是否允许调度任务单
        if(!clientOrderDomainService.mayScheduleTasks(transTasks)){
            throw new RuntimeException("全部或者部分任务单的当前状态不允许生成运单");
        }

        //创建运单
        TransOrder transOrder = dtoTransOrderAssembler.toEntity(transOrderCreateCmd);
        transOrderRepository.save(transOrder);

        //发布创建运单事件
        TransOrderCreateEvent transOrderCreateEvent = new TransOrderCreateEvent(
                transOrder.getId(), transOrderCreateCmd.getTransTasks().stream().findFirst().get().getOrderId(),
                transOrderCreateCmd.getTransTasks().stream().findFirst().get().getTaskId());
        guavaDomainEventPublisher.publish(transOrderCreateEvent);

        return Result.success();
    }

}
