package com.linkinsense.tmsorder.domain.service;

import com.linkinsense.tmsorder.domain.aggregate.order.ClientOrder;
import com.linkinsense.tmsorder.domain.aggregate.order.TransTask;
import com.linkinsense.tmsorder.domain.aggregate.order.valueobject.ClientOrderStatus;
import com.linkinsense.tmsorder.domain.aggregate.order.valueobject.TransTaskStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务逻辑处理放到领域服务中
 */
@Service
public class ClientOrderDomainService {
    /**
     * 检查订单是否允许更新状态
     * @param clientOrder
     * @param newStatus
     * @return
     */
    public Boolean mayAcceptOrderStatus(ClientOrder clientOrder, ClientOrderStatus newStatus){
        //订单未创建，不允许
        if(newStatus == ClientOrderStatus.CHECKED && clientOrder.getStatus()!=ClientOrderStatus.CREATED){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 检查任务单是否允许更新状态
     * @param clientOrder
     * @param transTask
     * @param newStatus
     * @return
     */
    public Boolean mayAcceptTaskStatus(ClientOrder clientOrder, TransTask transTask, TransTaskStatus newStatus){
        //订单未审核，不允许操作任务单
        if(clientOrder.getStatus() != ClientOrderStatus.CHECKED){
            return Boolean.FALSE;
        }
        //生成任务单(待计划状态)之前的状态必须是“已创建”
        if(newStatus == TransTaskStatus.PREPARED && transTask.getStatus() != TransTaskStatus.CREATED){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 检查是否允许计划调度任务单:所有任务单的状态必须是 ： 待计划
     * @param transTasks
     * @return
     */
    public Boolean mayScheduleTasks(List<TransTask> transTasks){
        return transTasks.stream().allMatch(e->e.getStatus()==TransTaskStatus.PREPARED);
    }
}
