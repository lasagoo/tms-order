package com.linkinsense.tmsorder.application.command.cmd;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class ClientOrderUpdateCmd {
    @NotNull(message = "客户ID不能为空")
    private Long clientId;

    @NotNull(message = "订单ID不能为空")
    private Long orderId;

    @NotNull(message = "订单编码不能为空")
    private String orderCode;

    @NotNull(message = "订单方向不能为空")
    private String orderDir;
}
