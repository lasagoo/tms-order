package com.linkinsense.tmsorder.infrastructure.db.converter;

import com.linkinsense.tmsorder.domain.aggregate.client.Client;
import com.linkinsense.tmsorder.infrastructure.db.dataobject.ClientDO;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter {
    public ClientDO serialize(Client client){
        ClientDO clientDO = new ClientDO();
        return clientDO;
    }
    public Client deserialize(ClientDO clientDO){
        return null;
    }
}
