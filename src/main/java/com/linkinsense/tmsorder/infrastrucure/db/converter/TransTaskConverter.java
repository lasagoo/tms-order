package com.linkinsense.tmsorder.infrastrucure.db.converter;

import com.linkinsense.tmsorder.domain.aggregate.client.Client;
import com.linkinsense.tmsorder.domain.aggregate.task.TransTask;
import com.linkinsense.tmsorder.infrastrucure.db.dataobject.ClientDO;
import com.linkinsense.tmsorder.infrastrucure.db.dataobject.TransTaskDO;
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
