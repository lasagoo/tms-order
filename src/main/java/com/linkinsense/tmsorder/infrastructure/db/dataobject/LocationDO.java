package com.linkinsense.tmsorder.infrastructure.db.dataobject;

import lombok.Data;

@Data
public class LocationDO {
    private Long id;
    private String code;
    private String name;
    private Long clientId;
    private String province;
    private String city;
    private String county;
    private String street;
    private Float longitude;
    private Float latitude;
}
