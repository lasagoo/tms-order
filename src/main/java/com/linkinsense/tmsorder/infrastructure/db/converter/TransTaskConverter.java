package com.linkinsense.tmsorder.infrastructure.db.converter;

import com.linkinsense.tmsorder.domain.aggregate.order.TransTask;
import com.linkinsense.tmsorder.infrastructure.db.dataobject.TransTaskDO;
import org.springframework.stereotype.Component;

@Component
public class TransTaskConverter {
    public TransTaskDO serialize(TransTask transTask){
        TransTaskDO transTaskDO = new TransTaskDO();
        transTaskDO.setId(transTask.getId());
        transTaskDO.setLoadingLocationId(transTask.getLoadingSpot().getId());
        transTaskDO.setUnloadingLocationId(transTask.getUnloadingSpot().getId());
        transTaskDO.setLoadingDate(transTask.getLoadingDate());
        transTaskDO.setArriveDate(transTask.getArriveDate());
        transTaskDO.setStatus(transTask.getStatus().getValue());
        return transTaskDO;
    }
    public TransTask deserialize(TransTaskDO transTaskDO){

        return new TransTask();
    }
}
