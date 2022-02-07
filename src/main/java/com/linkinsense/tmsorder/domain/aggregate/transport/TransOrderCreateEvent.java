package com.linkinsense.tmsorder.domain.aggregate.transport;

public class TransOrderCreateEvent {
    private Long transOrderId;
    private Long orderId;
    private Long taskId;

    public TransOrderCreateEvent(Long transOrderId, Long orderId, Long taskId) {
        this.transOrderId = transOrderId;
        this.orderId = orderId;
        this.taskId = taskId;
    }

    public Long getTransOrderId() {
        return transOrderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getTaskId() {
        return taskId;
    }
}
