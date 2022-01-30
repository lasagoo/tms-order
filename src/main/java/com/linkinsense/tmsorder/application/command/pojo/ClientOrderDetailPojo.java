package com.linkinsense.tmsorder.application.command.pojo;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ClientOrderDetailPojo {

    private Long id;

    @NotNull(message = "装货点不能为空")
    private Long loadingLocationId;

    @NotNull(message = "卸货点不能为空")
    private Long unloadingLocationId;

    @NotNull(message = "装车时间不能为空")
    private Date loadingDate;

    @NotNull(message = "送达时间不能为空")
    private Date arriveDate;
}
