package com.linkinsense.tmsorder.domain.aggregate.location.valueobject;

import lombok.Data;

@Data
public class Address {
    private String province;
    private String city;
    private String county;
    private String street;
    private Address addrDetail;
    private Float longitude;
    private Float latitude;
}
