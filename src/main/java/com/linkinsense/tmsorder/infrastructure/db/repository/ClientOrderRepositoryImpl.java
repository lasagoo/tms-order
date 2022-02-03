
package com.linkinsense.tmsorder.infrastructure.db.repository;

import com.linkinsense.tmsorder.domain.aggregate.order.ClientOrder;
import com.linkinsense.tmsorder.domain.aggregate.order.ClientOrderRepository;
import com.linkinsense.tmsorder.domain.aggregate.task.TransTask;
import com.linkinsense.tmsorder.infrastructure.db.converter.ClientOrderConverter;
import com.linkinsense.tmsorder.infrastructure.db.converter.TransTaskConverter;
import com.linkinsense.tmsorder.infrastructure.db.dataobject.ClientOrderDO;
import com.linkinsense.tmsorder.infrastructure.db.dataobject.TransTaskDO;
import com.linkinsense.tmsorder.infrastructure.db.mapper.ClientOrderMapper;
import com.linkinsense.tmsorder.infrastructure.db.mapper.TransTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ClientOrderRepositoryImpl implements ClientOrderRepository {

    @Autowired
    private ClientOrderMapper clientOrderMapper;

    @Autowired
    private TransTaskMapper transTaskMapper;

    @Autowired
    private TransTaskConverter transTaskConverter;

    @Autowired
    private ClientOrderConverter clientOrderConverter;

    @Override
    public ClientOrder find(Long id) {
        ClientOrderDO clientOrderDO = clientOrderMapper.select(id);
        if(clientOrderDO == null){
            throw new IllegalArgumentException(
                    "cannot find any item in client_order table with oder_id = " + id);
        }
        List<TransTaskDO> transTaskDOList = transTaskMapper.selectByOrderId(id);
        if(transTaskDOList == null){
            throw new IllegalArgumentException(
                    "cannot find any item in trans_task table with oder_id = " + id);
        }

        List<TransTask> transTasks = transTaskDOList.stream().
                map(e->transTaskConverter.deserialize(e)).
                collect(Collectors.toList());
        ClientOrder clientOrder = clientOrderConverter.deserialize(clientOrderDO);
        clientOrder.setTasks(transTasks);
        return clientOrder;
    }

    @Override
    public void save(ClientOrder clientOrder) {

        ClientOrderDO clientOrderDO = clientOrderConverter.serialize(clientOrder);
        clientOrderMapper.insert(clientOrderDO);

        List<TransTaskDO> transTaskDOS = clientOrder.getTasks().
                stream().map(e->transTaskConverter.serialize(e)).
                collect(Collectors.toList());

        transTaskDOS.forEach(e->{
            e.setOrderId(clientOrderDO.getId());
            transTaskMapper.insert(e);
        });
    }

    @Override
    public void update(ClientOrder clientOrder) {
        ClientOrderDO clientOrderDO = clientOrderConverter.serialize(clientOrder);
        clientOrderMapper.update(clientOrderDO);
    }
}
