package com.linkinsense.tmsorder.infrastrucure.db.converter;

import com.linkinsense.tmsorder.domain.aggregate.order.ClientOrder;
import com.linkinsense.tmsorder.infrastrucure.db.dataobject.ClientOrderDO;
import org.springframework.stereotype.Component;

@Component
public class ClientOrderConverter<ClientOrderD> {
    public ClientOrderDO serialize(ClientOrder clientOrder){
        return new ClientOrderDO();
    }

    public ClientOrder deserialize(ClientOrderDO clientOrderDO){
        return new ClientOrder();
    }
}
