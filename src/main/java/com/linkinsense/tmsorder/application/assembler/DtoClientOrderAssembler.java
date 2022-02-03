package com.linkinsense.tmsorder.application.assembler;

import com.linkinsense.tmsorder.application.command.cmd.ClientOrderCreateCmd;
import com.linkinsense.tmsorder.application.command.cmd.ClientOrderUpdateCmd;
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


    public ClientOrder toEntity(ClientOrderCreateCmd clientOrderCreateCmd){
        Client client = clientRepository.find(clientOrderCreateCmd.getClientId());
        if(client == null){
            throw new IllegalArgumentException("clientId " +
                    clientOrderCreateCmd.getClientId() + " not exist");
        }
        List<TransTask> transTasks = clientOrderCreateCmd.getDetails().stream()
                        .map(e->dtoTransTaskAssembler.toEntity(e))
                        .collect(Collectors.toList());
        ClientOrder clientOrder = new ClientOrder();
        clientOrder.setTasks(transTasks);
        clientOrder.setClient(client);
        clientOrder.setOrderCode(clientOrderCreateCmd.getOrderCode());
        clientOrder.setOrderDir(clientOrderCreateCmd.getOrderDir());
        return clientOrder;
    }

    public ClientOrder toEntity(ClientOrderUpdateCmd clientOrderUpdateCmd){
        Client client = clientRepository.find(clientOrderUpdateCmd.getClientId());
        if(client == null){
            throw new IllegalArgumentException("clientId " +
                    clientOrderUpdateCmd.getClientId() + " not exist");
        }
        ClientOrder clientOrder = new ClientOrder();
        clientOrder.setClient(client);
        clientOrder.setId(clientOrderUpdateCmd.getOrderId());
        clientOrder.setOrderCode(clientOrderUpdateCmd.getOrderCode());
        clientOrder.setOrderDir(clientOrderUpdateCmd.getOrderDir());
        return clientOrder;
    }
}
