package com.linkinsense.tmsorder.infrastrucure.db.converter;

import com.linkinsense.tmsorder.domain.aggregate.client.Client;
import com.linkinsense.tmsorder.infrastrucure.db.dataobject.ClientDO;

public class ClientConverter {
    public static ClientDO serialize(Client client){
        ClientDO clientDO = new ClientDO();
        return clientDO;
    }
    public static Client deserialize(ClientDO clientDO){
        return null;
    }
}
