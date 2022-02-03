package com.linkinsense.tmsorder.application.command.cmd;

import com.linkinsense.tmsorder.application.command.pojo.ClientOrderDetailPojo;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderDetailAddCmd {
    @NotNull
    private Long orderId;
    @NotNull
    private ClientOrderDetailPojo detail;
}
