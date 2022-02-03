package com.linkinsense.tmsorder.domain.aggregate.task;

public class TransTaskPlanEvent {
    private TransTask transTask;

    public TransTaskPlanEvent(TransTask transTask) {
        this.transTask = transTask;
    }

    public TransTask getTransTask() {
        return transTask;
    }
}
