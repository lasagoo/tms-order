package com.linkinsense.tmsorder.application.command.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TransOrderDetailPojo {

    @NotNull(message = "订单号不能为空")
    private Long orderId;

    @NotNull(message = "任务单号不能为空")
    private Long taskId;
}
