package com.linkinsense.tmsorder.domain.aggregate.order;

import com.linkinsense.tmsorder.domain.aggregate.client.Client;
import com.linkinsense.tmsorder.domain.aggregate.order.valueobject.ClientOrderStatus;
import com.linkinsense.tmsorder.domain.aggregate.order.valueobject.TransTaskStatus;
import lombok.Data;

import java.util.List;

/**
 * 任务单必须依赖订单而存在，任务单的生命周期在订单的生命周期之内，因此将
 * 订单设计成聚合根，包含了任务单实体；对任务单的操作都必须经过订单聚合根
 */
@Data
public class ClientOrder {
    private Long id;
    private String orderCode;
    private String orderDir;
    private Client client;
    private ClientOrderStatus status;
    private List<TransTask> tasks;


    public TransTask findTransTask(Long transTaskId){
        for (TransTask transTask:tasks
             ) {
            if(transTask.getId() == transTaskId){
                return transTask;
            }
        }
        return null;
    }

    /**
     * 切换订单的状态：已计划
     */
    public void switchScheduled(){
        //暂时不允许切换进入此状态
        if(!allowSchedule()){
            return;
        }
        //状态机混乱
        if(status != ClientOrderStatus.SCHEDULED){
            throw new RuntimeException("trans task cannot change into SCHEDULED status");
        }
        status = ClientOrderStatus.SCHEDULED;
    }


    /**
     * 检查订单是否允许进入 已计划 状态
     * 1、如果订单的任务单状态不一致，不允许切换到 已计划状态
     * 2、如果任务单的状态都一致，但不是 已计划 状态
     * @return
     */
    private Boolean allowSchedule(){
        TransTaskStatus firstStatus = tasks.get(0).getStatus();
        Boolean isConsitent = Boolean.TRUE;
        for (TransTask transTask:tasks) {
            if(transTask.getStatus() != firstStatus){
                isConsitent= Boolean.FALSE;
            }
        }
        if(!isConsitent){
            return Boolean.FALSE;
        }
        if(firstStatus != TransTaskStatus.SCHEDULED){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
