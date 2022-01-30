package com.linkinsense.tmsorder.application.command.impl;

import com.linkinsense.tmsorder.application.assembler.DtoClientOrderAssembler;
import com.linkinsense.tmsorder.application.command.ClientOrderCmdService;
import com.linkinsense.tmsorder.application.command.cmd.CreateClientOrderCommand;
import com.linkinsense.tmsorder.application.result.Result;
import com.linkinsense.tmsorder.domain.aggregate.client.Client;
import com.linkinsense.tmsorder.domain.aggregate.client.ClientRepository;
import com.linkinsense.tmsorder.domain.aggregate.order.ClientOrder;
import com.linkinsense.tmsorder.domain.aggregate.order.ClientOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class ClientOrderCmdServiceImpl implements ClientOrderCmdService {

    @Autowired
    ClientOrderRepository clientOrderRepository;
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    DtoClientOrderAssembler dtoClientOrderAssembler;

    @Override
    public <T> Result<T> createOrder(CreateClientOrderCommand cmd) {

        ClientOrder clientOrder = dtoClientOrderAssembler.toEntity(cmd);
        clientOrderRepository.save(clientOrder);

        return Result.success();
    }

    @Override
    public <T> Result<T> deleteOrder(Long orderId) {
        return Result.success();
    }
}
