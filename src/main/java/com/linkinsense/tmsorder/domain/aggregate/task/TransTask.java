package com.linkinsense.tmsorder.domain.aggregate.task;

import com.linkinsense.tmsorder.domain.aggregate.location.Location;
import lombok.Data;

import java.util.Date;

@Data
public class TransTask {
    private Long id;
    private Location loadingSpot;
    private Location unloadingSpot;
    private Date loadingDate;
    private Date arriveDate;
    private String status;
}
