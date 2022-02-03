package com.linkinsense.tmsorder.infrastrucure.db.dataobject;

import com.linkinsense.tmsorder.domain.aggregate.location.valueobject.Address;
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
