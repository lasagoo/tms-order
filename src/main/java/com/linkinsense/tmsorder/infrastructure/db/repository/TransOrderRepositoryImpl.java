package com.linkinsense.tmsorder.infrastructure.db.repository;

import com.linkinsense.tmsorder.domain.aggregate.transport.TransOrder;
import com.linkinsense.tmsorder.domain.aggregate.transport.TransOrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TransOrderRepositoryImpl implements TransOrderRepository {
    @Override
    public TransOrder find(Long id) {
        return null;
    }

    @Override
    public void save(TransOrder transOrder) {

    }

    @Override
    public void update(TransOrder transOrder) {

    }
}
