package com.linkinsense.tmsorder.domain.aggregate.client;

import lombok.Data;

import java.util.Date;

@Data
public class Client {
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
