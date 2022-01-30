package com.linkinsense.tmsorder.application.assembler;

import com.linkinsense.tmsorder.application.command.cmd.CreateClientOrderCommand;
import com.linkinsense.tmsorder.domain.aggregate.client.Client;
import com.linkinsense.tmsorder.domain.aggregate.client.ClientRepository;
import com.linkinsense.tmsorder.domain.aggregate.order.ClientOrder;
import com.linkinsense.tmsorder.domain.aggregate.order.ClientOrderRepository;
import com.linkinsense.tmsorder.domain.aggregate.task.TransTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoClientOrderAssembler {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClientOrderRepository clientOrderRepository;
    @Autowired
    private DtoTransTaskAssembler dtoTransTaskAssembler;

    public ClientOrder toEntity(CreateClientOrderCommand createClientOrderCommand){
        Client client = clientRepository.find(createClientOrderCommand.getClientId());
        if(client == null){
            throw new IllegalArgumentException("clientId " +
                    createClientOrderCommand.getClientId() + " not exist");
        }
        List<TransTask> transTasks = createClientOrderCommand.getDetails().stream()
                        .map(e->dtoTransTaskAssembler.toEntity(e))
                        .collect(Collectors.toList());
        ClientOrder clientOrder = new ClientOrder();
        clientOrder.setTasks(transTasks);
        clientOrder.setClient(client);
        clientOrder.setOrderCode(createClientOrderCommand.getOrderCode());
        clientOrder.setOrderDir(createClientOrderCommand.getOrderDir());
        return clientOrder;
    }
}
