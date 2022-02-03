package com.linkinsense.tmsorder.domain.aggregate.order;

import com.linkinsense.tmsorder.domain.aggregate.client.Client;
import com.linkinsense.tmsorder.domain.aggregate.order.valueobject.OrderStatus;
import com.linkinsense.tmsorder.domain.aggregate.task.TransTask;
import com.linkinsense.tmsorder.domain.aggregate.task.valueobject.TransTaskStatus;
import lombok.Data;

import java.util.List;

@Data
public class ClientOrder {
    private Long id;
    private String orderCode;
    private String orderDir;
    private Client client;
    private OrderStatus status;
    private List<TransTask> tasks;

    public void setScheduledStatus(){
        if(!allowToScheduledStatus()){
            return;
        }
        if(!status.nextStatus().equals(OrderStatus.SCHEDULED)){
            throw new RuntimeException("trans task cannot change into SCHEDULED status");
        }
        status = OrderStatus.SCHEDULED;
    }


    private Boolean allowToScheduledStatus(){
        TransTaskStatus firstStatus = tasks.get(0).getStatus();
        Boolean isConsitent = Boolean.TRUE;
        for (TransTask transTask:tasks) {
            if(!transTask.getStatus().equals(firstStatus)){
                isConsitent= Boolean.FALSE;
            }
        }
        if(!isConsitent){
            return Boolean.FALSE;
        }
        if(!firstStatus.equals(TransTaskStatus.SCHEDULED)){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
