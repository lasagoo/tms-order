package com.linkinsense.tmsorder.infrastrucure.db.repository;

import com.linkinsense.tmsorder.domain.aggregate.client.Client;
import com.linkinsense.tmsorder.domain.aggregate.client.ClientRepository;
import com.linkinsense.tmsorder.infrastrucure.db.converter.ClientConverter;
import com.linkinsense.tmsorder.infrastrucure.db.dataobject.ClientDO;
import com.linkinsense.tmsorder.infrastrucure.db.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public Client find(Long id) {
        ClientDO clientDO =clientMapper.select(id);
        Client client = ClientConverter.deserialize(clientDO);
        return client;
    }

    @Override
    public void save(Client client) {

    }

    @Override
    public void remove(Long id) {

    }
}
