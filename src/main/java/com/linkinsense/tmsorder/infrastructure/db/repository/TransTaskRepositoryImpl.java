package com.linkinsense.tmsorder.infrastructure.db.repository;

import com.linkinsense.tmsorder.domain.aggregate.order.TransTask;
import com.linkinsense.tmsorder.domain.aggregate.order.TransTaskRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TransTaskRepositoryImpl implements TransTaskRepository {

    @Override
    public TransTask find(Long id) {
        return null;
    }

    @Override
    public void save(TransTask transTask) {

    }

    @Override
    public void update(TransTask transTask) {

    }
}
