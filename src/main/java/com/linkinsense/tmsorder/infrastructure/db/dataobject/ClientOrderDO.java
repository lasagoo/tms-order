package com.linkinsense.tmsorder.infrastructure.db.dataobject;


import lombok.Data;

@Data
public class ClientOrderDO {
    private Long id;
    private String orderCode;
    private String orderDir;
    private Long clientId;
    private String status;
}
