package com.linkinsense.tmsorder.application.command.cmd;

import com.linkinsense.tmsorder.application.command.pojo.ClientOrderDetailPojo;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Data
public class ClientOrderCreateCmd {

    @NotNull(message = "客户ID不能为空")
    private Long clientId;

    @Null(message = "创建订单不能指定ID")
    private Long orderId;

    @Null(message = "创建订单不能指定订单号")
    private String orderCode;

    @NotNull(message = "订单方向不能为空")
    private String orderDir;

    @NotNull(message = "订单详情不能为空")
    private List<ClientOrderDetailPojo> details;

}
