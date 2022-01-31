package com.linkinsense.tmsorder.domain.aggregate.location;

import com.linkinsense.tmsorder.domain.aggregate.client.Client;
import com.linkinsense.tmsorder.domain.aggregate.location.valueobject.Address;
import lombok.Data;

@Data
public class Location {
    private Long id;
    private String name;
    private Client client;
    private Address address;
}
