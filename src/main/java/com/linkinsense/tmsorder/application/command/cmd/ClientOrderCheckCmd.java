package com.linkinsense.tmsorder.application.command.cmd;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ClientOrderCheckCmd {
    @NotNull(message = "订单ID不能为空")
    private Long orderId;

    @NotNull(message = "订单检查结果不能为空")
    private Boolean isPass;
}
