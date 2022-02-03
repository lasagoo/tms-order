package com.linkinsense.tmsorder.domain.aggregate.task;

import com.linkinsense.tmsorder.domain.aggregate.location.Location;
import com.linkinsense.tmsorder.domain.aggregate.task.valueobject.TransTaskStatus;
import lombok.Data;

import java.util.Date;

@Data
public class TransTask {
    private Long id;
    private Long orderId;
    private Location loadingSpot;
    private Location unloadingSpot;
    private Date loadingDate;
    private Date arriveDate;
    private TransTaskStatus status;

}
