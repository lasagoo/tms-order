package com.linkinsense.tmsorder.domain.aggregate.order.valueobject;

public enum TransTaskStatus {
    CREATED("已创建"),
    PREPARED("待计划"),
    SCHEDULED("已计划"),
    ASSIGNED("已分配"),
    EXPORTED("已出库"),
    TRANSFERRED("已交接"),
    TRANSPORTING("运输中"),
    SIGNED("已签收");


    private String value;
    TransTaskStatus(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }



}
