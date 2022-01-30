package com.linkinsense.tmsorder.application.assembler;

import com.linkinsense.tmsorder.application.command.pojo.ClientOrderDetailPojo;
import com.linkinsense.tmsorder.domain.aggregate.location.Location;
import com.linkinsense.tmsorder.domain.aggregate.location.LocationRepository;
import com.linkinsense.tmsorder.domain.aggregate.task.TransTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;

@Component
public class DtoTransTaskAssembler {

    @Autowired
    LocationRepository locationRepository;

    public TransTask toEntity(ClientOrderDetailPojo clientOrderDetailPojo){

        @NotNull
        Location loadingLocation = locationRepository.find(clientOrderDetailPojo.getLoadingLocationId());
        @NotNull
        Location unloadingLocation = locationRepository.find(clientOrderDetailPojo.getUnloadingLocationId());

        TransTask transTask = new TransTask();
        transTask.setId(clientOrderDetailPojo.getId());
        transTask.setLoadingSpot(loadingLocation);
        transTask.setUnloadingSpot(unloadingLocation);
        transTask.setLoadingDate(clientOrderDetailPojo.getLoadingDate());
        transTask.setArriveDate(clientOrderDetailPojo.getArriveDate());
        return transTask;
    }
}
