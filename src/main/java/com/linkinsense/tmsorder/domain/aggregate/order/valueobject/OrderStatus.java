package com.linkinsense.tmsorder.domain.aggregate.order.valueobject;

import com.linkinsense.tmsorder.domain.aggregate.task.valueobject.TransTaskStatus;
import lombok.Data;

public enum OrderStatus {
    CREATED("已创建"),
    PLANING("待计划"),
    SCHEDULED("已计划"),
    ASSIGNED("已分配"),
    TRANSPORTING("运输中"),
    SIGNED("已签收");

    private String value;
    OrderStatus(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public OrderStatus nextStatus(){
        if(this.equals(PLANING)){
            return SCHEDULED;
        }
        return CREATED;
    }
}
