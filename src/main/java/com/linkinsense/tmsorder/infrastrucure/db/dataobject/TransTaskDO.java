package com.linkinsense.tmsorder.infrastrucure.db.dataobject;

import lombok.Data;

import java.util.Date;

@Data
public class TransTaskDO {
    private Long id;
    private long loadingLocationId;
    private long unloadingLocationId;
    private Date loadingDate;
    private Date arriveDate;
    private String status;
}
