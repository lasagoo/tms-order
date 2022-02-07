package com.linkinsense.tmsorder.domain.aggregate.transport;

import com.linkinsense.tmsorder.domain.aggregate.order.TransTask;

public interface TransOrderRepository {
    TransOrder find(Long id);
    void save(TransOrder transOrder);
    void update(TransOrder transOrder);
}
