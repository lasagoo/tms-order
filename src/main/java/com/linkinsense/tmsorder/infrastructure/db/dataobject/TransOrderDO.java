package com.linkinsense.tmsorder.infrastructure.db.dataobject;

import lombok.Data;

@Data
public class TransOrderDO {
    private Long id;
    private String code;
    private Long driverId;
    private String status;
}
