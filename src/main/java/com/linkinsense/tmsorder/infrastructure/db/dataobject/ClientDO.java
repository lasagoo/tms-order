package com.linkinsense.tmsorder.infrastructure.db.dataobject;

import lombok.Data;

import java.util.Date;

@Data
public class ClientDO {
    private Long id;
    private String code;
    private String abbr;
    private String addr;
    private String contact;
    private String mail;
    private String phone;
    private String status;
    private Date createTime;
    private Date modifyTime;
}
