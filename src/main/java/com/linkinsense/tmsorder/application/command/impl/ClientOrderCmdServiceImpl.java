package com.linkinsense.tmsorder.application.command.impl;

import com.linkinsense.tmsorder.application.assembler.DtoClientOrderAssembler;
import com.linkinsense.tmsorder.application.assembler.DtoTransTaskAssembler;
import com.linkinsense.tmsorder.application.command.ClientOrderCmdService;
import com.linkinsense.tmsorder.application.command.cmd.ClientOrderCheckCmd;
import com.linkinsense.tmsorder.application.command.cmd.ClientOrderUpdateCmd;
import com.linkinsense.tmsorder.application.command.cmd.OrderDetailAddCmd;
import com.linkinsense.tmsorder.application.command.cmd.ClientOrderCreateCmd;
import com.linkinsense.tmsorder.application.result.Result;
import com.linkinsense.tmsorder.domain.aggregate.client.ClientRepository;
import com.linkinsense.tmsorder.domain.aggregate.order.*;
import com.linkinsense.tmsorder.domain.aggregate.order.valueobject.ClientOrderStatus;
import com.linkinsense.tmsorder.domain.aggregate.order.valueobject.TransTaskStatus;
import com.linkinsense.tmsorder.domain.aggregate.transport.TransOrderCreateEvent;
import com.linkinsense.tmsorder.domain.service.ClientOrderDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@Service
public class ClientOrderCmdServiceImpl implements ClientOrderCmdService {

    @Autowired
    ClientOrderRepository clientOrderRepository;
    @Autowired
    TransTaskRepository transTaskRepository;
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    DtoClientOrderAssembler dtoClientOrderAssembler;
    @Autowired
    DtoTransTaskAssembler dtoTransTaskAssembler;

    @Autowired
    ClientOrderDomainService clientOrderDomainService;

    @Transactional
    @Override
    public <T> Result<T> createOrder(ClientOrderCreateCmd cmd) {

        ClientOrder clientOrder = dtoClientOrderAssembler.toEntity(cmd);
        clientOrderRepository.save(clientOrder);
        return Result.success();
    }

    @Override
    public <T> Result<T> deleteOrder(Long orderId) {
        return Result.success();
    }

    @Override
    public <T> Result<T> updateOrder(ClientOrderUpdateCmd cmd) {

        ClientOrder clientOrder = dtoClientOrderAssembler.toEntity(cmd);
        clientOrderRepository.update(clientOrder);
        return Result.success();
    }

    @Override
    public <T> Result<T> checkOrder(ClientOrderCheckCmd clientOrderCheckCmd) {
        ClientOrder clientOrder = clientOrderRepository.find(clientOrderCheckCmd.getOrderId());
        if(clientOrder == null){
            throw new IllegalArgumentException("orderId: " + clientOrderCheckCmd.getOrderId() + " not exist");
        }

        ClientOrderStatus clientOrderStatus = ClientOrderStatus.CREATED;
        if(clientOrderCheckCmd.getIsPass() == Boolean.TRUE){
            clientOrderStatus = ClientOrderStatus.CHECKED;
        }
        else{
            clientOrderStatus = ClientOrderStatus.CREATED;
        }
        if(clientOrderDomainService.mayAcceptOrderStatus(clientOrder, clientOrderStatus) == Boolean.FALSE){
            throw new RuntimeException("orderId: " + clientOrderCheckCmd.getOrderId() +" cannot change to CHECKED status");
        }
        clientOrder.setStatus(clientOrderStatus);
        clientOrderRepository.update(clientOrder);
        return Result.success();
    }

    @Override
    public void handleTransTaskPlanEvent(TransTaskPlanEvent transTaskPlanEvent) {
        ClientOrder clientOrder = clientOrderRepository.find(transTaskPlanEvent.getTransTask().getOrderId());
        if(clientOrder == null){
            throw new IllegalArgumentException("orderId: " + transTaskPlanEvent.getTransTask().getOrderId() + "not exist");
        }
        clientOrder.switchScheduled();
        clientOrderRepository.update(clientOrder);
        return;
    }

    /**
     * 使用的是同步事件，因此接收端与发射端仍然在同一个事务里面
     * 只是代码上看起来是分离的，符合每个事务只更新一个聚合根的原则
     * @param transOrderCreateEvent
     */
    @Override
    public void handleTransOrderCreateEvent(TransOrderCreateEvent transOrderCreateEvent) {
        ClientOrder clientOrder = clientOrderRepository.find(transOrderCreateEvent.getOrderId());
        TransTask transTask = transTaskRepository.find(transOrderCreateEvent.getTaskId());
        if(clientOrder == null || transTask == null){
            throw new IllegalArgumentException("事件接收异常:任务单或者订单不存在");
        }
        clientOrder.setStatus(ClientOrderStatus.SCHEDULED);
        transTask.setStatus(TransTaskStatus.SCHEDULED);
        clientOrderRepository.save(clientOrder);
        transTaskRepository.save(transTask);
    }
}
