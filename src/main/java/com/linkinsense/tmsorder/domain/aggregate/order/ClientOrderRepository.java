package com.linkinsense.tmsorder.domain.aggregate.order;

public interface ClientOrderRepository {
    ClientOrder find(Long id);
    void save(ClientOrder clientOrder);
}
