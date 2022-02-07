package com.linkinsense.tmsorder.domain.aggregate.order.valueobject;


public enum ClientOrderStatus {
    CREATED("已创建"),
    CHECKED("已审核"),
    PREPARED("待计划"),
    SCHEDULED("已计划"),
    ASSIGNED("已分配"),
    TRANSPORTING("运输中"),
    SIGNED("已签收");

    private String value;
    ClientOrderStatus(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}
