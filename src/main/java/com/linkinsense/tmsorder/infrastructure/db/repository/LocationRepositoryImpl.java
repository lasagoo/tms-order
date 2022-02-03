package com.linkinsense.tmsorder.infrastructure.db.repository;

import com.linkinsense.tmsorder.domain.aggregate.location.Location;
import com.linkinsense.tmsorder.domain.aggregate.location.LocationRepository;
import org.springframework.stereotype.Repository;

@Repository
public class LocationRepositoryImpl implements LocationRepository {
    @Override
    public Location find(Long id) {
        return null;
    }
}
