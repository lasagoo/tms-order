package com.linkinsense.tmsorder.domain.aggregate.order;

import com.linkinsense.tmsorder.domain.aggregate.client.Client;
import com.linkinsense.tmsorder.domain.aggregate.order.valueobject.OrderStatus;
import com.linkinsense.tmsorder.domain.aggregate.task.TransTask;
import lombok.Data;

import java.util.List;

@Data
public class ClientOrder {
    private Long id;
    private String orderCode;
    private String orderDir;
    private Client client;
    private OrderStatus status;
    private List<TransTask> tasks;
}
