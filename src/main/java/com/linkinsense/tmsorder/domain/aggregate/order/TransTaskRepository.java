package com.linkinsense.tmsorder.domain.aggregate.order;

import com.linkinsense.tmsorder.domain.aggregate.order.TransTask;

public interface TransTaskRepository {
    TransTask find(Long id);
    void save(TransTask transTask);
    void update(TransTask transTask);
}
