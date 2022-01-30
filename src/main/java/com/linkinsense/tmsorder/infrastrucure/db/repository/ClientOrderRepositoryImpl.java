package com.linkinsense.tmsorder.infrastrucure.db.repository;

import com.linkinsense.tmsorder.domain.aggregate.order.ClientOrder;
import com.linkinsense.tmsorder.domain.aggregate.order.ClientOrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ClientOrderRepositoryImpl implements ClientOrderRepository {

    @Override
    public ClientOrder find(Long id) {
        return null;
    }

    @Override
    public void save(ClientOrder clientOrder) {

    }
}
