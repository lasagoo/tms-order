package com.linkinsense.tmsorder.application.command.impl;

import com.linkinsense.tmsorder.application.assembler.DtoClientOrderAssembler;
import com.linkinsense.tmsorder.application.assembler.DtoTransTaskAssembler;
import com.linkinsense.tmsorder.application.command.ClientOrderCmdService;
import com.linkinsense.tmsorder.application.command.cmd.ClientOrderUpdateCmd;
import com.linkinsense.tmsorder.application.command.cmd.OrderDetailAddCmd;
import com.linkinsense.tmsorder.application.command.cmd.ClientOrderCreateCmd;
import com.linkinsense.tmsorder.application.result.Result;
import com.linkinsense.tmsorder.domain.aggregate.client.ClientRepository;
import com.linkinsense.tmsorder.domain.aggregate.order.ClientOrder;
import com.linkinsense.tmsorder.domain.aggregate.order.ClientOrderRepository;
import com.linkinsense.tmsorder.domain.aggregate.task.TransTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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
        return null;
    }

    @Override
    public <T> Result<T> addOrderDetail(OrderDetailAddCmd cmd) {
        ClientOrder clientOrder = clientOrderRepository.find(cmd.getOrderId());
        if(clientOrder == null){
            throw new IllegalArgumentException("client order id " +
                    cmd.getOrderId() + "not exist");
        }
        transTaskRepository.save(dtoTransTaskAssembler.toEntity(cmd.getDetail()));
        return Result.success();
    }


}
