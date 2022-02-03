package com.linkinsense.tmsorder.infrastrucure.db.repository;

import com.linkinsense.tmsorder.domain.aggregate.task.TransTask;
import com.linkinsense.tmsorder.domain.aggregate.task.TransTaskRepository;
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
}
