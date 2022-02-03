package com.linkinsense.tmsorder.infrastrucure.db.converter;

import com.linkinsense.tmsorder.domain.aggregate.location.Location;
import com.linkinsense.tmsorder.infrastrucure.db.dataobject.LocationDO;
import org.springframework.stereotype.Component;

@Component
public class LocationConverter {

    public LocationDO serialize(Location location){
        return new LocationDO();
    }

    public Location deserialize(LocationDO locationDO){
        return new Location();
    }
}
