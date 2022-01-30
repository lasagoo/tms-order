package com.linkinsense.tmsorder.domain.aggregate.order;

import com.linkinsense.tmsorder.domain.aggregate.client.Client;
import com.linkinsense.tmsorder.domain.aggregate.task.TransTask;
import lombok.Data;

import java.util.List;

@Data
public class ClientOrder {
    private Long orderId;
    private String orderCode;
    private String orderDir;
    private Client client;
    private List<TransTask> tasks;
}
