package com.linkinsense.tmsorder.domain.aggregate.task;

public interface TransTaskRepository {
    TransTask find(Long id);
    void save(TransTask transTask);
}
