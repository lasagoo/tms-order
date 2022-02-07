package com.linkinsense.tmsorder.application.command.cmd;

import com.linkinsense.tmsorder.application.command.pojo.TransOrderDetailPojo;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class TransOrderCreateCmd {

    @NotNull(message = "运单详情不能为空")
    List<TransOrderDetailPojo> transTasks;
}
